package jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import common.CommonVar;

public class GetProperties {
	public static String getProp(String key) throws IOException {
		// 获取action上下文
		ActionContext context = ActionContext.getContext();
		// 获取请求对象
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		String projectPath = request.getSession().getServletContext().getRealPath("/");
		String propertyFilePath = projectPath + "WEB-INF/classes/properties/DBConn.properties";
		CommonVar.classPath = propertyFilePath;
		Properties prop = new Properties();
		FileInputStream ips = new FileInputStream(CommonVar.classPath);
		String result = "";
		try {
			prop.load(ips);
			result = prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ips.close();
		}
		return result;
	}
}
