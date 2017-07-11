package com.zjgsu.service.impl;

import com.zjgsu.dao.*;
import com.zjgsu.entity.*;
import com.zjgsu.service.HrmService;
import com.zjgsu.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zby on 2017/7/10.
 */
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
@Service("hrmService")
public class HrmServiceImpl implements HrmService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private JobDao jobDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private DocumentDao documentDao;

    /********************************************用户服务接口实现**************************************************/
    @Transactional(readOnly = true)
    public User login(String loginname, String password) {
        System.out.println("HrmServiceImpl login -- >>");
        return userDao.selectByUsernameAndPassword(loginname,password);
    }

    @Transactional(readOnly = true)
    public User findUserById(Integer id) {
        System.out.println("HrmServiceImpl findUserById -- >>");
        return userDao.selectById(id);
    }

    // TODO 分页查询参数需要校正
    @Transactional(readOnly = true)
    public List<User> findUser(User user, PageModel pageModel) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("user",user);
        int recordCount = userDao.count(params);
        System.out.println("HrmServiceImpl findUser -- >>" + recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0){
            /** 开始分页查询数据:查询第几页的数据 */
            params.put("pageModel",pageModel);
        }
        List<User> users = userDao.selectByPage(params);
        return users;
    }

    public void removeUserById(Integer id) {
        System.out.println("HrmServiceImpl removeUserById -- >>");
        userDao.deleteById(id);
    }

    public void modifyUser(User user) {
        System.out.println("HrmServiceImpl modifyUser -- >>");
        userDao.update(user);
    }

    public void addUser(User user) {
        System.out.println("HrmServiceImpl addUser -- >>");
        userDao.save(user);
    }

    /********************************************员工服务接口实现**************************************************/
    @Transactional(readOnly = true)
    public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("employee",employee);
        int recordCount = employeeDao.count(params);
        System.out.println("HrmServiceImpl findEmployee -- >>" + recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0){
            params.put("pageModel",pageModel);
        }
        List<Employee> employees = employeeDao.selectByPage(params);
        return employees;
    }

    public void removeEmployeeById(Integer id) {
        System.out.println("HrmServiceImpl removeEmployeeById -- >>");
        employeeDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Employee findEmployeeById(Integer id) {
        System.out.println("HrmServiceImpl findEmployeeById -- >>");
        return employeeDao.selectById(id);
    }

    public void addEmployee(Employee employee) {
        System.out.println("HrmServiceImpl addEmployee -- >>");
        employeeDao.save(employee);
    }

    public void modifyEmployee(Employee employee) {
        System.out.println("HrmServiceImpl modifyEmployee -- >>");
        employeeDao.update(employee);
    }

    /********************************************部门服务接口实现**************************************************/
    @Transactional(readOnly = true)
    public List<Dept> findDept(Dept dept, PageModel pageModel) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("dept",dept);
        int recordCount = deptDao.count(params);
        pageModel.setRecordCount(recordCount);
        System.out.println("HrmServiceImpl findDept -- >>" + recordCount);
        if (recordCount > 0){
            params.put("pageModel",pageModel);
        }
        List<Dept> depts = deptDao.selectByPage(params);
        return depts;
    }

    @Transactional(readOnly = true)
    public List<Dept> findAllDept() {
        System.out.println("HrmServiceImpl findAllDept -- >>");
        return deptDao.selectAllDept();
    }

    public void removeDeptById(Integer id) {
        System.out.println("HrmServiceImpl removeDeptById -- >>");
        deptDao.deleteById(id);
    }

    public void addDept(Dept dept) {
        System.out.println("HrmServiceImpl addDept -- >>");
        deptDao.save(dept);
    }

    @Transactional(readOnly = true)
    public Dept findDeptById(Integer id) {
        System.out.println("HrmServiceImpl findDeptById -- >>");
        return deptDao.selectById(id);
    }

    public void modifyDept(Dept dept) {
        System.out.println("HrmServiceImpl modifyDept -- >>");
        deptDao.update(dept);
    }

    /********************************************职位服务接口实现**************************************************/
    @Transactional(readOnly = true)
    public List<Job> findJob(Job job, PageModel pageModel) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("job",job);
        int recordCount = jobDao.count(params);
        pageModel.setRecordCount(recordCount);
        System.out.println("HrmServiceImpl findJob -- >>" + recordCount);
        if (recordCount > 0){
            params.put("pageModel",pageModel);
        }
        List<Job> jobs = jobDao.selectByPage(params);
        return jobs;
    }

    @Transactional(readOnly = true)
    public List<Job> findAllJob() {
        System.out.println("HrmServiceImpl findAllJob -- >>");
        return jobDao.selectAllJob();
    }

    public void removeJobById(Integer id) {
        System.out.println("HrmServiceImpl removeJobById -- >>");
        jobDao.deleteById(id);
    }

    public void addJob(Job job) {
        System.out.println("HrmServiceImpl addJob -- >>");
        jobDao.save(job);
    }

    @Transactional(readOnly = true)
    public Job findJobById(Integer id) {
        System.out.println("HrmServiceImpl findJobById -- >>");
        return jobDao.selectById(id);
    }

    public void modifyJob(Job job) {
        System.out.println("HrmServiceImpl modifyJob -- >>");
        jobDao.update(job);
    }

    /********************************************公告服务接口实现**************************************************/
    @Transactional(readOnly = true)
    public List<Notice> findNotice(Notice notice, PageModel pageModel) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("notice",notice);
        int recordCount = noticeDao.count(params);
        pageModel.setRecordCount(recordCount);
        System.out.println("HrmServiceImpl findNotice -- >>" + recordCount);
        if (recordCount > 0){
            params.put("pageModel",pageModel);
        }
        List<Notice> notices = noticeDao.selectByPage(params);
        return notices;
}

    @Transactional(readOnly = true)
    public Notice findNoticeById(Integer id) {
        System.out.println("HrmServiceImpl findNoticeById -- >>");
        return noticeDao.selectById(id);
    }

    public void removeNoticeById(Integer id) {
        System.out.println("HrmServiceImpl removeNoticeById -- >>");
        noticeDao.deleteById(id);
    }

    public void addNotice(Notice notice) {
        System.out.println("HrmServiceImpl addNotice -- >>");
        noticeDao.save(notice);
    }

    public void modifyNotice(Notice notice) {
        System.out.println("HrmServiceImpl modifyNotice -- >>");
        noticeDao.update(notice);
    }

    /********************************************文件服务接口实现**************************************************/
    @Transactional(readOnly = true)
    public List<Document> findDocument(Document document, PageModel pageModel) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("document",document);
        int recordCount = documentDao.count(params);
        pageModel.setRecordCount(recordCount);
        System.out.println("HrmServiceImpl findDocument -- >>" + recordCount);
        if (recordCount > 0){
            params.put("pageModel",pageModel);
        }
        List<Document> documents = documentDao.selectByPage(params);
        return documents;
    }

    public void addDocument(Document document) {
        System.out.println("HrmServiceImpl addDocument -- >>");
        documentDao.save(document);
    }

    @Transactional(readOnly = true)
    public Document findDocumentById(Integer id) {
        System.out.println("HrmServiceImpl findDocumentById -- >>");
        return documentDao.selectById(id);
    }

    public void removeDocumentById(Integer id) {
        System.out.println("HrmServiceImpl removeDocumentById -- >>");
        documentDao.deleteById(id);
    }

    public void modifyDocument(Document document) {
        System.out.println("HrmServiceImpl modifyDocument -- >>");
        documentDao.update(document);
    }
}
