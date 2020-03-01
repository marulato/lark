package org.avalon.lark.common.docgen;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import org.avalon.lark.common.consts.SystemConsts;
import org.avalon.lark.common.utility.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class TemplatePdfGenerator {

    private String docName;
    public static final Rectangle A0 = PageSize.A0;
    public static final Rectangle A1 = PageSize.A1;
    public static final Rectangle A2 = PageSize.A2;
    public static final Rectangle A3 = PageSize.A3;
    public static final Rectangle A4 = PageSize.A4;
    public static final Rectangle A5 = PageSize.A5;
    public static final Rectangle A6 = PageSize.A6;
    public static final Rectangle A4_LAYOUT = A4.rotate();
    public static final Rectangle A3_LAYOUT = A3.rotate();

    public TemplatePdfGenerator(String docName) {
        this.docName = docName;
    }

    public abstract Map<String, Object> getParameters(HttpServletRequest request);

    public abstract String getTemplate();

    public byte[] generatePdf(HttpServletRequest request) throws Exception {
        return generatePdf(request, new Document(PageSize.A4, 60, 60, 50, 50), true);
    }

    public byte[] generatePdf(HttpServletRequest request, Rectangle pageSize) throws Exception {
        return generatePdf(request, new Document(pageSize, 60, 60, 50, 50), true);
    }

    public byte[] generatePdf(HttpServletRequest request, Document doc, boolean needPageNo) throws Exception {
        String template = getTemplate();
        Map<String, Object> params = getParameters(request);
        String html = TemplateMessageProvider.INSTANCE.getTemplateMessage(template, params);
        BaseGenerator gen = new PdfGenerator();
        gen.setNeedPageNo(needPageNo);
        if (params != null) {
            String stamp = (String) params.get("stamp");
            if (StringUtils.isNotEmpty(stamp)) {
                gen.setStamp(stamp);
            }
            String pageHeader = (String) params.get("pageHeader");
            if (StringUtils.isNotEmpty(pageHeader)) {
                gen.setPageHeader(pageHeader);
            }
        }

        String cssFile = SystemConsts.getClassPath() + "css/doc.css";

        return gen.generate(html, cssFile, doc);
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }
}


