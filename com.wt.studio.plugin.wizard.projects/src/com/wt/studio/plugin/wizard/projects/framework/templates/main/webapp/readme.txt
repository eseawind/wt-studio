####################################################################
#
#	http://localhost:8080/uum2/rest/user/id/liyi  地址用于验证UUM2服务
#
#####################################################################

1.2011-04-29号，uum2有相关jar包更新，请大家及时更新一下lib文件下的一系统jar
 
2.在rabc包下IndexAction.java 添加超级管理员，该管理员用户名和密码写在
com.hirisun.hea.common.config.hea_constant.property文件中，注意，密码不是明文显示，已经过md5加密！
 
3.要想应用框架能跑起来，需要uum2的服务支持（本地环境），
第一步：配置jndi数据源，需在类似C:\Users\MM\Desktop\apache-tomcat-6.0.20\webapps\conf\context.xml 的文件里添加jndi配置（weblogic需进入adminconsole控制台手工添加jndi数据源），
添加的代码片断如下：
<Resource auth="Container" driverClassName="oracle.jdbc.driver.OracleDriver"
name="jdbc/uum" password="uumdba_test" type="javax.sql.DataSource"
url="jdbc:oracle:thin:@192.168.20.38:1521:hirisuntest" username="uum_test" />
 
第二步：把uum2.war包布到tomcat的webapp下


第三步：指定src包下的uum-rest-client.properties内的SERVER_URL的ip或端口，改成你布署的uum2包的ip和端口


http://localhost:8080/hea/heaRptStatRelaOrgAction.hea?action=test