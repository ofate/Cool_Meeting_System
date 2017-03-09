package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.net.httpserver.HttpContext;

import common.CommonVar;

public class InitAction implements ModelDriven {
	private HttpContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;

	/**
	 * @return the context
	 */
	public HttpContext getContext() {
		return context;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public void setContext(HttpContext context) {
		this.context = context;
	}

	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * @param request
	 *            the request to set
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * @return the response
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @param response
	 *            the response to set
	 */
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void InitProcessing() {
		ActionContext context = ActionContext.getContext();
		request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/html;charset=UTF-8");

		CommonVar.classPath = request.getSession().getServletContext().getRealPath("/");
		System.out.println(CommonVar.classPath);
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}
}
