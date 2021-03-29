# GreenDaoDemo
 greenDAO是适用于Android的轻量级快速ORM，可将对象映射到SQLite数据库。针对Android进行了高度优化，greenDAO提供了出色的性能，并占用了最少的内存。（特对greenDAO写个案例，顺便熟悉greenDAO的相关使用）

# 在项目中添加 `greenDAO` 库

1、在根 `build.gradle` 文件中:
```
buildscript {
    repositories {
        jcenter()
        mavenCentral() // add repository
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.5.3'
        classpath 'org.greenrobot:greendao-gradle-plugin:3.3.0' // add plugin
    }
}
```

2、在 `app/build.gradle` 文件中:
```
apply plugin: 'com.android.application'
apply plugin: 'org.greenrobot.greendao' // apply plugin

android {
    ....

    greendao {
        //TODO 需要新建entity，再编译
        schemaVersion 1
        daoPackage 'com.xxxx.greendaodemo.db.tableDao'  //设置DaoMaster、DaoSession、Dao包名
        targetGenDir 'src/main/java'
    }
}
 
dependencies {
    implementation 'org.greenrobot:greendao:3.3.0' // add library
}
```
4、新建一张简单的数据库表、并添加注释
```
@Entity(indexes = {
    @Index(value = "text, date DESC", unique = true)
})
public class Note {

    @Id
    private Long id;

    @NotNull
    private String text;
    private Date date;
    ...
```

5、新建App.class 继承自 Application.class，并在AndroidManifest.xml中添加App.class引用。
`案例对greenDAO简单封装了，以DBManager为唯一入口，获取XXXHelp.class（继承自BaseHelp.class, 可在XXXHelp.class中添加相应的）对象，进行数据库操作`
```
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化数据库
        DBManager.newInstance().setDataBase(this);
    }
}
```
```
  <application
        android:name=".App"
        ...
```

# 在使用中，需要注意一些问题

```
1、新建第一张数据库表类entity,再编译，才能出现`daoPackage`
```

```
2、增加或修改数据库表类entity，都需要再编译；对已编译过的数据库表类entity进行修改，需要删除编译产生的构造方法（带`@Generated(hash =xxxxx)`注解的构造方法），再编译（才能起作用）。
```

```
3、数据库表类entity的 Id 字段必须定义为Long类型 
```









