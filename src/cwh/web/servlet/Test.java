package cwh.web.servlet;

import cwh.utils.concurrent.ThreadUtils;
import cwh.utils.log.VSLog;
import cwh.web.session.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cwh on 16-1-7
 */
@WebServlet(name = "Test")
public class Test extends HttpServlet {
    public static String TAG = "Test";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String sid = request.getParameter(CommonDefine.SID);
//        Object obj;
//        if ((obj = getServletContext().getAttribute(sid)) == null) {
//            VSLog.d(TAG, "obj null");
//            obj = sid + "obj";
//            getServletContext().setAttribute(sid, obj);
//        }
//        VSLog.d(obj.toString());
        ThreadUtils.sleep(1000);
        ServletHelper.responseString(response, ServletHelper.genErrCode(0, request.getSession().toString()));
        VSLog.d(TAG, request.getSession().toString());
        VSLog.d(TAG, request.getServletContext().toString());
        VSLog.d(TAG, SessionManager.getInstance().toString());
    }
}
