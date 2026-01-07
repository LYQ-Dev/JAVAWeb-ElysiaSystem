package com.lyq.mapper;

import com.lyq.pojo.Emp;
import com.lyq.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

            /*
        查询全部员工数据
         */
    @Select("SELECT * from emp")
    List<Emp> getAllEmps();

//    原始分页查询的基础较为底层的写法
//    /**
//     * 查询总记录数
//     */
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id ")
//    public Long count();    //这条语句：什么参数都不传入。接着将SQL语句的查询结果自动返回到一个Long类型的对象里面，也就是说只要调用了Mapper的这个方法就可以得到一个Long类型的返回值，包含SQL语句的结果
//
//    /**
//     * 查询所有的员工及其对应的部门名称
//     */
//    @Select("select e.*, d.name deptName from emp as e left join dept as d on e.dept_id = d.id limit #{start}, #{pageSize}")
//    public List<Emp> allEmpAndDept(Integer start , Integer pageSize);    //类似于上面那句，但是从参数从这传入上方的SQL语句

    //分页查询实现，就是一条简单的SQL语句，不用实现查询总记录，分页实现不在这体现
//    @Select("select e.*, d.name deptName from emp as e left join dept as d on e.dept_id = d.id")


    //分页查询功能，配置了EmpMapper.xml文件中的动态SQL语句，这里不用再写SQL语句
    List<Emp> fenyeGet(EmpQueryParam empQueryParam);


    //添加员工数据
    @Options(useGeneratedKeys = true, keyProperty = "id")   //mybatis的主键返回功能
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void addemp(Emp emp);
    //执行完 INSERT 新增 SQL 后，数据库自增生成的主键值，会自动赋值到传入的 Emp 对象的 id 属性中，这样就能直接获取这条新数据的主键。

    //按ID删除员工
    //由于传入的是一个集合，带有多个id，同理在empmapper.xml配置SQL语句
    void deleteByIds(List<Integer> ids);

    //按ID删除员工经历，同理在empmapper.xml中配置SQL语句
    void deleteExprByIds(List<Integer> ids);

    //根据id查询员工详细信息（包括工作经历），同理在文件中配置SQL语句
    Emp getInfo(Integer id);

    //更新员工数据，在文件中配置SQL
    void updateEmp(Emp emp);

    /**
     * 统计各个职位的员工人数
     */
    @MapKey("pos")  //实际这一行是不需要的，这样只是为了消除“假报错”
    List<Map<String, Object>> countEmpJobData();
    /**
     * 统计员工性别信息
     */
    @MapKey("name")
    List<Map> countEmpGenderData();

    /**
     * 根据部门ID查询员工数量
     */
    Integer getNumByDeptId(Integer deptid);

    /**
     * 根据用户名和密码查询员工
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getUsernameAndPassword(Emp emp);
}
