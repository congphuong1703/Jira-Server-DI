package com.company.jiraplugin.phase1;
import javax.servlet.http.HttpServletRequest;

public class UtilsHelper {

    public static String getContextPathFromHttpRequest(HttpServletRequest request){
        String url = "";
        String base = request.getContextPath();

        Integer post = request.getServerPort();
        String sPost = "";

        if(post > 0){
            sPost =  ":" + post ;
        }

        url = request.getScheme() + "://" + request.getServerName() + sPost + base ;
        return url;
    }
}
