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
`本案例对greenDAO简单封装了，以DBManager为唯一入口，获取XXXHelp.class（继承自BaseHelp.class, 可在XXXHelp.class中添加相应的，一表一类制）对象，进行数据库操作`
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
1、数据库表类entity的 Id 字段必须定义为Long类型 
```

```
2、新建第一张数据库表类entity,再编译，才能出现 daoPackage
```

```
3、增加或修改数据库表类entity，都需要再编译；对已编译过的数据库表类entity进行修改，需要删除编译产生的构造方法（带`@Generated(hash =xxxxx)`注解的构造方法），再编译（才能起作用）。
```

```
4、由于greenDAO 3.0 生成的字段添加了非空约束。字段的类型设置为基本类型(如:int)默认会添加非空约束,字段类型设置为对象类型(如:Integer)默认不会添加非空约束,而且最终生成的sql会使用对象类型
```

```
5、实体类实现 Serializable 接口报错
解决方法：这个时候添加一行
@Transient
private static final long serialVersionUID = 1L;
```

# greenDAO注解的含义及用途

### 1、@Entity:表明这个实体类会在数据库中生成一个与之相对应的表
```
注解@Entity中的属性：
schema：告知GreenDao当前实体属于哪个 schema
active：标记一个实体处于活跃状态，活动实体有更新、删除和刷新方法
nameInDb：在数据库中使用的别名，默认使用的是实体的类名，
indexes：定义索引，可以跨越多个列
createInDb：标记创建数据库表（默认：true）
generateConstructors 自动创建全参构造方法（同时会生成一个无参构造方法）（默认：true）
generateGettersSetters 自动生成 getters and setters 方法（默认：true）
```
```
使用：
@Entity(
        schema = "myschema",
        active = true,
        nameInDb = "AWESOME_USERS"，
        indexes = {
                @Index(value = "name DESC", unique = true)
        },
        createInDb = true,
        generateConstructors = false,
        generateGettersSetters = true
)
public class User {
  ...
}
```

### 2、@Id：对应数据表中的 Id 字段
```
@Entity
public class User{
    @Id(autoincrement = true)//主键自动增长
    private Long id;
      ...
}
```

### 3、@Generated：编译后自动生成的构造函数、方法等的注释，提示构造函数、方法等不能被修改
### 4、@NotNull：设置数据库表当前列不能为空
### 5、@Unique：表名该属性在数据库中只能有唯一值
### 6、@Transient：添加此标记后不会生成数据库表的列

### 7、@Index：使用@Index作为一个属性来创建一个索引，默认是使用字段名
```
@Entity
public class User {
    @Id 
    private Long id;

    @Index(unique = true)
    private String name;
}
```

### 8、@ToOne 一对一
```
```


### 9、@ToMany 一对多

### 10、@Property：设置一个非默认关系映射所对应的列名，默认是使用字段名,例如：

```
@Entity (nameInDb = “User”)
public class User {
@Property(nameInDb = “userName”)
private String userName;

}
```

### 11、@OrderBy：更加某一字段排序 ，例如：@OrderBy(“date ASC”)
```
@OrderBy("date ASC")
private List<Order> orders;
```