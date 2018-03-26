package zhuiyun.xyz.injectutils;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by gwy on 2018/3/26.
 *
 * @author:zhuiyun
 */

public class InjectUtils {
    //上一次点击的时间
    private static long lastClickTime;
    //在activity中使用
    public static void inject(Activity activity){
        inject(new InjectAdapter(activity),activity);
    }

    //在view中使用
    public static void inject(View view){
        inject(new InjectAdapter(view),view);
    }

    //在fragment中使用
    public  static void inject(View view,Object object){
            inject(new InjectAdapter(view),object);
    }

    private static  void inject(InjectAdapter adapter,Object object){
        //注入控件
        injectView(adapter,object);
        //注入事件
        injectEvent(adapter,object);
    }

    /**
     *
     * @param adapter
     * @param object
     */
    private static void injectEvent(InjectAdapter adapter, Object object) {
        Class<?> clz=object.getClass();
        Method[] declaredMethods = clz.getDeclaredMethods();
        for (Method method:declaredMethods) {
            //获取方法的注解
            BindClick annotation = method.getAnnotation(BindClick.class);
            ClickInterval clickInterval=method.getAnnotation(ClickInterval.class);
            boolean needCheckClickInterval=false;//是否需要检查重复点击
            long duration=0;
            if (clickInterval!= null) {
                //表示要防重复点击
                needCheckClickInterval=true;
                duration=clickInterval.value();
            }
            if(annotation!=null){
                //拿到需要注入事件的view
                int[] viewId=annotation.value();
                for (int id : viewId) {
                    View view=adapter.findViewById(id);
                    if(view!=null){
                        view.setOnClickListener(new DeclaredOnClickListener(method,object,needCheckClickInterval,duration));
                    }

                }
            }
        }
    }

    /**
     *
     * @param adapter
     * @param object
     */
    private static void injectView(InjectAdapter adapter, Object object) {
        Class<?> clz=object.getClass();
        //获取所有属性
        Field[] declaredFields = clz.getDeclaredFields();
        for (Field field:declaredFields) {
            //获取属性的注解
            BindView annotation = field.getAnnotation(BindView.class);
            //如果注解不为空,则代表这是要绑定view的
            if(annotation!=null){
                //获取viewid
                int viewId = annotation.value();
                View view=adapter.findViewById(viewId);
                //如果view的id写错,view会为空
                if (view != null) {
                    try {
                        //设置可以访问私有属性
                        field.setAccessible(true);
                        field.set(object,view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     *
     */
    private static class DeclaredOnClickListener implements View.OnClickListener {
        private Method method;
        private Object object;
        private boolean needCheckClickInterval;
        private long duration;

        public DeclaredOnClickListener(Method method, Object object, boolean needCheckClickInterval, long duration) {
            this.method = method;
            this.object = object;
            this.needCheckClickInterval=needCheckClickInterval;
            this.duration=duration;
        }

        @Override
        public void onClick(View v) {
            if(needCheckClickInterval){
                if(System.currentTimeMillis()-lastClickTime<duration){
                    return;
                }
            }
            method.setAccessible(true);
            //更新上次点击时间
            lastClickTime=System.currentTimeMillis();
            try {
                method.invoke(object,v);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    method.invoke(object,new Object[]{});
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (InvocationTargetException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
