# InjectUtils
一个注解框架,绑定控件,点击事件,防重复点击

使用:

- 绑定控件:

  ```
   @BindView(R.id.tv)
   private TextView tv;
   
  ```


- 绑定点击事件且防重复点击

  ```
   @BindClick(R.id.tv)
   @ClickInterval(5000)
   public void play(){
       tv.setText("bindClick");

   }
  ```

  ​