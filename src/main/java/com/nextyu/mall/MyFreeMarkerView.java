package com.nextyu.mall;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * created on 2017-11-07 15:45
 *
 * @author nextyu
 */
public class MyFreeMarkerView extends FreeMarkerView {
    private static final String CONTEXT_PATH = "base";

    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        String appContext = request.getContextPath();
        StringBuilder basePath = new StringBuilder();
        basePath.append(request.getScheme()).append("://");
        basePath.append(request.getServerName());
        if (request.getServerPort() != 80) {
            basePath.append(":").append(request.getServerPort());
        }

        basePath.append(appContext);
        model.put("base", basePath.toString());
        super.exposeHelpers(model, request);
    }
}
