# Mybatis SQL相关问题 

## 1.插入数据
id不自增长
<insert id="addBlog" parameterType="Blog">
        insert into blog (id,txt,title,createTime,updateTime,author,authorid)
        values (#{id},#{txt},#{title},#{createTime},#{updateTime},#{author},#{authorid})
</insert>
设置id自增长
方式一：
<insert id="addBlog" parameterType="Blog"  useGeneratedKeys="true" keyProperty="id" keyColumn="id">
        insert into blog (txt,title,createTime,updateTime,author,authorid)
        values (#{txt},#{title},#{createTime},#{updateTime},#{author},#{authorid})
</insert>
方式二：
<insert id="addBlog" parameterType="Blog"   >
    <selectKey keyProperty="id" order="AFTER" resultType="java.lang.Integer">
        SELECT LAST_INSERT_ID()
    </selectKey>
    insert into blog (txt,title,createTime,updateTime,author,authorid)
    values (#{txt},#{title},#{createTime},#{updateTime},#{author},#{authorid})
</insert>

## 2.模糊查询
方式一：where title like '%${title}%'
方式二：where title like concat('%', #{title}   ,'%')
方式三：where title like concat('%', '${title}' ,'%')
方式二和三借助concat函数，可避免sql注入问题
' #{ } '是预编译处理，MyBatis在处理#{ }时，它会将sql中的#{ }替换为？，然后调用PreparedStatement的set方法来赋值，传入字符串后，会在值两边加上单引号，使用占位符的方式提高效率，可以防止sql注入。
  ${} 表示拼接sql串，将接收到参数的内容不加任何修饰拼接在sql中，可能引发sql注入。

## 3.动态sql
更新操作时，可能不需要对所有字段更新，这时不需要更新的字段需要保持原字段信息
这时就需要进行动态SQL拼接，如下，使用trim就是为了删掉最后字段的“,”。
主要不用单独写SET了，因为set被包含在trim中了：
<update id="updateInfoById"  >
        UPDATE info
        <trim prefix="set" suffixOverrides=",">
            <if test="name!=null">name=#{name},</if>
            <if test="mail!=null">Dmail=#{mail},</if>
            <if test="phone!=null">phone=#{phone},</if>
            <if test="message!=null">message=#{message},</if>
            <if test="createTime!=null">createTime=#{createTime},</if>
        </trim>
        WHERE id=#{id}
</update>


## .其他注意事项
1.mybatisSQL语句中有多个不同参数类型时将parameterType属性去掉
2.select返回多条记录即返回类型为list时,但resultType还是写成resultType="Author"（Author为集合list中的实体类），而不是写成resultType="java.util.List"


#  
数据验证时 @Size 和@Length差不多，最大值都要和数据库字段长度匹配
@NotEmpty :不能为null，且Size>0
@NotNull:不能为null，但可以为empty,没有Size的约束
@NotBlank:只用于String,不能为null且trim()之后size>0

# Thymeleaf相关问题
## 1.使用th:object="${author} 前后端双向绑定时，需要从传入一个实体对象

## 2.thymeleaf引入静态资源
CSS文件放在static文件夹下的css文件夹里：<link th:href="@{/css/bootstrap-min.css}" rel="stylesheet" type="text/css">
JS文件放在static文件夹下的js文件夹里:<script th:src="@{/js/jquery-min.js}"  ></script>
注意问题：jquery.min.js文件名改成jquery-min.js 否则无法读取

## 3.th:each显示次数设置
无次数限制：th:each="blog : ${blogs}"
只显示五次：th:each="blog,blogStat:${blogs}" th:if="${blogStat.count} <= 5"

## 4.使用thymeleaf引入其他html页面,相当于jsp的include用法
头部引用：<header  th:replace="common/文件名::id或者标签名"></header>
一般：<div th:include="文件名 :: id或者标签名"></div>

th:include：引入子模块的children，依然保留父模块的tag。
加载模板的内容： 读取加载节点的内容（不含节点名称），替换div内容。
th:replace：引入子模块的所有，不保留父模块的tag。
替换当前标签为模板中的标签，加载的节点会整个替换掉加载他的div。
