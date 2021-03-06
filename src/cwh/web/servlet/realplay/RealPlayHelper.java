package cwh.web.servlet.realplay;

import cwh.utils.StringUtils;
import cwh.utils.concurrent.ThreadUtils;
import cwh.utils.log.VSLog;
import cwh.web.model.CommonDefine;
import cwh.web.model.realplay.AsyncRealPlay;
import cwh.web.servlet.ServletHelper;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by cwh on 16-1-2
 */
public class RealPlayHelper {
    public static String TAG = "RealPlayHelper";

    public static void asyncResponse(HttpServletRequest request, AsyncListener asyncListener) {
        AsyncContext asyncContext = request.startAsync();
        asyncContext.setTimeout(60 * 1000);
        asyncContext.addListener(asyncListener);
        ThreadUtils.runInBackGround(new AsyncRealPlay(asyncContext));
    }

    public static boolean isParamOk(HttpServletRequest request) {
        // ip=192.168.199.108&port=554&channel=1&sid=12121212

        String ip = request.getParameter(CommonDefine.IP);
        if (StringUtils.isEmpty(ip)) {
            return false;
        }
        if (!StringUtils.isMatch(ip, StringUtils.REGX_IP)) {
            return false;
        }

        String port = request.getParameter(CommonDefine.PORT);
        if (StringUtils.isEmpty(port)) {
            return false;
        }
        if (StringUtils.isMatch(port, StringUtils.REGX_POS_INT)) {
            int intPort = Integer.parseInt(port);
            if (intPort > 65535 || intPort < 1) {
                return false;
            }
        }

        String nvrIp = request.getParameter(CommonDefine.NVR_IP);
        if (StringUtils.isEmpty(nvrIp)) {
            return false;
        }
        if (!StringUtils.isMatch(nvrIp, StringUtils.REGX_IP)) {
            return false;
        }

        String nvrPort = request.getParameter(CommonDefine.PORT);
        if (StringUtils.isEmpty(nvrPort)) {
            return false;
        }
        if (StringUtils.isMatch(nvrPort, StringUtils.REGX_POS_INT)) {
            int intPort = Integer.parseInt(nvrPort);
            if (intPort > 65535 || intPort < 1) {
                return false;
            }
        }

        String sid = request.getParameter(CommonDefine.SID);
        if (!StringUtils.isEmpty(sid)) {
            if (!StringUtils.isMatch(sid, ServletHelper.REGX_SID)) {
                VSLog.e(TAG, "sid illegal :" + request.getQueryString());
                return false;
            }
        }
        return true;
    }
}
