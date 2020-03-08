package org.avalon.lark.common.servlet;

import org.avalon.lark.common.consts.AppConsts;
import org.avalon.lark.common.utility.EncryptionUtils;
import org.avalon.lark.common.utility.LogUtils;
import org.avalon.lark.common.utility.MappingUtils;
import org.avalon.lark.common.utility.StringUtils;
import org.avalon.lark.common.validation.Validation;
import org.avalon.lark.common.validation.ValidationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class AjaxValidationServlet {

    private static final Logger log = LoggerFactory.getLogger(AjaxValidationServlet.class);

    @PostMapping("/web/validation")
    public ValidationResult doValidate(@RequestBody Map<String, String> param, HttpServletResponse response)  {

        try {
            String dto = EncryptionUtils.decryptAES(param.get("dtoId"), AppConsts.WEB_ENC_KEY, true);
            if (StringUtils.isEmpty(dto)) {
                response.setStatus(400);
                log.warn(LogUtils.join("Unexpected calling detected", param));
                return null;
            }
            Class dtoClass = Class.forName(dto);
            ValidationResult result = Validation.validate(MappingUtils.getBean(param, dtoClass));
            result.setCode("200");
            return result;
        } catch (Exception e) {
            ValidationResult result = new ValidationResult();
            result.setCode("500");
            result.setMsg("Server Exception");
            log.error("", e);
            return result;
        }
    }
}
