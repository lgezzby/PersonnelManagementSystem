CREATE DATABASE personnelmanagement;
USE personnelmanagement;

# 创建表dept_inf
CREATE TABLE dept_inf (
  id INT(11) NOT NULL AUTO_INCREMENT ,
  name VARCHAR(50) NOT NULL ,
  remark VARCHAR(300) DEFAULT null ,
  PRIMARY KEY (id)
)ENGINE = INNODB AUTO_INCREMENT = 10 DEFAULT CHARSET = utf8;
INSERT INTO dept_inf (id, name, remark) VALUES (1,'技术部','技术部');
INSERT INTO dept_inf (id, name, remark) VALUES (2,'运营部','运营部');
INSERT INTO dept_inf (id, name, remark) VALUES (3,'财务部','技术部');
INSERT INTO dept_inf (id, name, remark) VALUES (5,'总公办','总公办');
INSERT INTO dept_inf (id, name, remark) VALUES (6,'市场部','市场部');
INSERT INTO dept_inf (id, name, remark) VALUES (7,'教学部','教学部');

# 创建表job_inf
CREATE TABLE job_inf(
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL ,
  remark VARCHAR(300) DEFAULT NULL ,
  PRIMARY KEY (id)
)ENGINE = INNODB AUTO_INCREMENT = 10 DEFAULT CHARSET = utf8;
INSERT INTO job_inf (id, name, remark) VALUES (1,'职员','职员');
INSERT INTO job_inf (id, name, remark) VALUES (2,'Java开发工程师','Java开发工程师');
INSERT INTO job_inf (id, name, remark) VALUES (3,'Java中级开发工程师','Java中级开发工程师');
INSERT INTO job_inf (id, name, remark) VALUES (4,'Java高级开发工程师','Java高级开发工程师');
INSERT INTO job_inf (id, name, remark) VALUES (5,'系统管理员','系统管理员');
INSERT INTO job_inf (id, name, remark) VALUES (6,'架构师','架构师');
INSERT INTO job_inf (id, name, remark) VALUES (7,'主管','主管');
INSERT INTO job_inf (id, name, remark) VALUES (8,'经理','经理');
INSERT INTO job_inf (id, name, remark) VALUES (9,'总经理','总经理');

# 创建user_inf
CREATE TABLE user_inf(
  id INT(11) NOT NULL AUTO_INCREMENT,
  loginname VARCHAR(20) NOT NULL ,
  password VARCHAR(16) NOT NULL ,
  status INT(11) NOT NULL DEFAULT '1',
  create_date TIMESTAMP NOT NULL DEFAULT current_timestamp,
  username VARCHAR(20) DEFAULT NULL ,
  PRIMARY KEY (id)
)ENGINE = INNODB AUTO_INCREMENT = 2 DEFAULT CHARSET = utf8;
INSERT INTO user_inf (id, loginname, password, status, create_date, username) VALUES (1,'admin','123456',2,'2017-07-07 13:23:30','超级管理员');

# 创建employee_inf
CREATE TABLE employee_inf(
  id INT(11) NOT NULL AUTO_INCREMENT,
  dept_id INT(11) NOT NULL ,
  job_id INT(11) NOT NULL ,
  name VARCHAR(20) NOT NULL ,
  card_id VARCHAR(18) NOT NULL ,
  address VARCHAR(50) NOT NULL ,
  post_code VARCHAR(50) DEFAULT NULL ,
  tel VARCHAR(16) DEFAULT NULL ,
  phone VARCHAR(11) NOT NULL ,
  qq_num VARCHAR(10) DEFAULT NULL ,
  email VARCHAR(50) NOT NULL ,
  sex INT(11) NOT NULL DEFAULT '1',
  party VARCHAR(10) DEFAULT NULL ,
  birthday DATETIME DEFAULT NULL ,
  race VARCHAR(100) DEFAULT NULL ,
  education VARCHAR(10) DEFAULT NULL ,
  speciality VARCHAR(20) DEFAULT NULL ,
  hobby VARCHAR(100) DEFAULT NULL ,
  remark VARCHAR(500) DEFAULT NULL ,
  create_date TIMESTAMP NOT NULL DEFAULT current_timestamp,
  PRIMARY KEY (id),
  KEY fk_emp_dept (dept_id),
  KEY fk_emp_job (job_id),
  CONSTRAINT fk_emp_dpt FOREIGN KEY (dept_id) REFERENCES dept_inf(id),
  CONSTRAINT fk_emp_job FOREIGN KEY (job_id) REFERENCES job_inf(id)
) ENGINE = INNODB AUTO_INCREMENT = 21 DEFAULT CHARSET = utf8;
INSERT INTO
  employee_inf(id, dept_id, job_id, name, card_id, address, post_code, tel,
               phone, qq_num, email, sex, party, birthday, race, education,
               speciality, hobby, remark, create_date)
VALUES
  (1,1,8,'爱丽丝','4328011988','广州天河','510000','020-77777777',
     '1390200111','36750066','251425887@qq.com',0,'党员','1981-01-01 00:00:00',
   '满','本科','美声','唱歌','四大天王','2016-03-14 11:35:18'),
  (2,2,1,'杰克','22623','43234','4247424','4224',
  '4247424','42424','251425887@qq.com',2,NULL,NULL,NULL,
  NULL,NULL,NULL,NULL,'2016-03-14 11:35:18'),
  (3,1,2,'bb','43280119771251038','广州','510000','020-99999999',
  '13907351532','36750064','36750064@qq.com',1,'党员','1977-11-25 00:00:00',
  '汉','本科','计算机','爬山','无','2016-07-14 09:54:52');

# 创建notice_inf
CREATE TABLE notice_inf(
  id INT(11) NOT NULL AUTO_INCREMENT,
  title VARCHAR(50) NOT NULL ,
  content TEXT NOT NULL ,
  create_date TIMESTAMP NOT NULL DEFAULT current_timestamp,
  user_id INT(11) DEFAULT NULL ,
  PRIMARY KEY (id),
  KEY fk_notice_user (user_id),
  CONSTRAINT fk_notice_user FOREIGN KEY (user_id) REFERENCES user_inf(id)
)ENGINE = INNODB AUTO_INCREMENT = 19 DEFAULT CHARSET = utf8;

# 创建document_inf
CREATE TABLE document_inf(
  id INT(11) NOT NULL AUTO_INCREMENT,
  title VARCHAR(50) NOT NULL ,
  filename VARCHAR(300) NOT NULL ,
  remark VARCHAR(300) DEFAULT NULL ,
  create_date TIMESTAMP NOT NULL DEFAULT current_timestamp,
  user_id INT(11) DEFAULT NULL ,
  PRIMARY KEY (id),
  KEY fk_document_user (user_id),
  CONSTRAINT fk_document_user FOREIGN KEY (user_id) REFERENCES user_inf(id)
)ENGINE = INNODB AUTO_INCREMENT = 7 DEFAULT CHARSET =utf8;