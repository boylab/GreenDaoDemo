﻿﻿﻿# GreenDaoDemo
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
3、增加或修改数据库表类entity，都需要再编译；对已编译过的数据库表类entity进行修改，
需要删除编译产生的构造方法（带`@Generated(hash =xxxxx)`注解的构造方法），再编译（才能起作用）。
```

```
4、由于greenDAO 3.0 生成的字段添加了非空约束。
字段的类型设置为基本类型(如:int)默认会添加非空约束,
字段类型设置为对象类型(如:Integer)默认不会添加非空约束,而且最终生成的sql会使用对象类型
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
@Entity
public class Husband {
    ...

    //在Husband表中，添加wifiID
    private Long wifiID;

    在insert时，将wifiID同一对一关联的Wife的主键id绑定
    @ToOne(joinProperty = "wifiID")
    private Wife wife;
	...
}
```

### 9、@ToMany 对多
#### `一对多`
```
@Entity
public class Leader {
    ...

    @ToMany(referencedJoinProperty = "leaderId")
    private List<Member> memberList;
    ...
}
```
```
@Entity
public class Member {
    ...

    @NotNull
    private Long leaderId;  //在Member表中，添加leaderId
    ...
}

```
#### `多对多`(需要建中间表)
```
@Entiry
public class Teacher {
    ...

    @ToMany
    @JoinEntity(
            entity = JoinTeachers.class,    //中间表
            sourceProperty = "tid",         //实体属性
            targetProperty = "sid"          //外链实体属性
    )
    private List<Student> students;
    ...
}
```
```
@Entity
public class Student {
    ...

    // 对多，@JoinEntity注解
    @ToMany
    @JoinEntity(
            entity = JoinTeachers.class,    //中间表
            sourceProperty = "sid",         //实体属性
            targetProperty = "tid"          //外链实体属性
    )
    private List<Teacher> teachers;
```
```
@Entity
public class JoinTeachers {
    @Id(autoincrement = true)
    private Long id;
    private Long tid;   //老师id
    private Long sid;   //学生id
}
```

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
# greenDAO提供的原始方法的作用及解释

### 1、DAO增加
```
long insert(T entity)                    // 插入指定实体
void insertInTx(T... entities)
void insertInTx(java.lang.Iterable<t> entities)
void insertInTx(java.lang.Iterable<t> entities, boolean setPrimaryKey)
long insertWithoutSettingPk(T entity)    // 插入指定实体，无主键
long insertOrReplace(T entity)           // 插入或替换指定实体
void insertOrReplaceInTx(T... entities)
void insertOrReplaceInTx(java.lang.Iterable<t> entities)
void insertOrReplaceInTx(java.lang.Iterable<t> entities, boolean setPrimaryKey)
void save(T entity)                      // 依赖指定的主键插入或修改实体
void saveInTx(T... entities)
void saveInTx(java.lang.Iterable<t> entities)
```

### 2、DAO删除
```
void deleteAll()             // 删除所有
void delete(T entity)        // 删除指定的实体
void deleteInTx(T... entities)
void deleteInTx(java.lang.Iterable<t> entities)
void deleteByKey(K key)      // 删除指定主键对应的实体
void deleteByKeyInTx(K... keys)
void deleteByKeyInTx(java.lang.Iterable<k> keys)
```

### 3、DAO查询
```
List<T> loadAll()
T load(K key)
T loadByRowId(long rowId)
```
```
queryBuilder()  // greenDAO下主要查询方法

QueryBuilder<T> where(WhereCondition cond, WhereCondition... condMore)                          // 条件，AND 连接
QueryBuilder<T> whereOr(WhereCondition cond1, WhereCondition cond2, WhereCondition... condMore) // 条件，OR 连接
QueryBuilder<T> distinct()                          // 去重，例如使用联合查询时
QueryBuilder<T> limit(int limit)                    // 限制返回数
QueryBuilder<T> offset(int offset)                  // 偏移结果起始位，配合limit(int)使用
QueryBuilder<T> orderAsc(Property... properties)    // 排序，升序
QueryBuilder<T> orderDesc(Property... properties)   // 排序，降序
QueryBuilder<T> orderCustom(Property property, String customOrderForProperty)  // 排序，自定义
QueryBuilder<T> orderRaw(String rawOrder)           // 排序，SQL 语句
QueryBuilder<T> preferLocalizedStringOrder()        // 本地化字符串排序，用于加密数据库无效
QueryBuilder<T> stringOrderCollation(String stringOrderCollation)   // 自定义字符串排序，默认不区分大小写
WhereCondition and(WhereCondition cond1, WhereCondition cond2, WhereCondition... condMore)  // 条件，AND 连接
WhereCondition or(WhereCondition cond1, WhereCondition cond2, WhereCondition... condMore)   // 条件，OR 连接
```

### 4、DAO修改
```
void update(T entity)
void updateInTx(T... entities)
void updateInTx(java.lang.Iterable<t> entities)
```
### 5、DAO其他
```
void refresh(T entity)      // 从数据库获取值刷新本地实体
long count()                // 数量
boolean detach(T entity)    // 从域中分离实体
void detachAll()            // 从域中分离所有实体
```
# greenDAO 对时间的操作
```
待研究，待完善
Date???   String???     TimeMillis???
```

# R8, ProGuard混淆
在 `proguard-rules.pro`中添加混淆
```
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
    public static java.lang.String TABLENAME;
}
-keep class **$Properties { *; }

# If you DO use SQLCipher:
-keep class org.greenrobot.greendao.database.SqlCipherEncryptedHelper { *; }

# If you do NOT use SQLCipher:
-dontwarn net.sqlcipher.database.**
# If you do NOT use RxJava:
-dontwarn rx.**
```