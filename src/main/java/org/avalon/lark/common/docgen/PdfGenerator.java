package org.avalon.lark.common.docgen;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.avalon.lark.common.utility.StringUtils;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;


public class PdfGenerator extends BaseGenerator {

    public PdfGenerator() {
        needPageNo = true;
    }

    @Override
    public byte[] generate(String html) throws Exception {
        return generate(html, null);
    }

    public byte[] generate(String html, String css, Document doc) throws Exception {
        byte[] pdf = null;
       // Document doc = new Document(size, 60, 60, 50, 50);
        ByteArrayOutputStream out = null;
        PdfWriter writer = null;
        BufferedInputStream bufferedIn = null;
        FileInputStream cssIn = null;
		try {
			out = new ByteArrayOutputStream();
			writer = PdfWriter.getInstance(doc, out);
            BaseFont baseFont = BaseFont.createFont("STSong-Light",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
            Font font = new Font(baseFont);
			doc.open();
			if (StringUtils.isNotEmpty(css)) {
				bufferedIn = new BufferedInputStream(new ByteArrayInputStream(html.getBytes("UTF-8")));
				cssIn = new FileInputStream(css);
			    XMLWorkerHelper.getInstance().parseXHtml(writer, doc, bufferedIn, cssIn);
			} else {
				bufferedIn = new BufferedInputStream(new ByteArrayInputStream(html.getBytes("UTF-8")));
			    XMLWorkerHelper.getInstance().parseXHtml(writer, doc, bufferedIn);
			}
		} catch (Exception e) {
			throw e;
		} finally {
		    bufferedIn.close();
		    cssIn.close();
		    doc.close();
		    writer.close();
		    out.close();
		}
		if (out != null) {
			pdf = out.toByteArray();
		}

        if (needPageNo || !StringUtils.isEmpty(pageHeader) || !StringUtils.isEmpty(pageFooter)) {
            ByteArrayOutputStream newOut = new ByteArrayOutputStream();
            PdfReader reader = new PdfReader(pdf);
            PdfStamper stamper = new PdfStamper(reader, newOut);
            int n = reader.getNumberOfPages();
            for (int i = 1; i <= n; i++) {
                if (!StringUtils.isEmpty(stamp) || !StringUtils.isEmpty(pageHeader)) {
                    int yPos = !StringUtils.isEmpty(stamp) && !StringUtils.isEmpty(pageHeader) ? 830 : 820;
                    getHeaderTable(i, n).writeSelectedRows(0, -1, 40, yPos, stamper.getOverContent(i));
                }
                if (needPageNo || !StringUtils.isEmpty(pageFooter)) {
                    getFooterTable(i, n).writeSelectedRows(0, -1, 40, 30, stamper.getOverContent(i));
                }
            }
            stamper.close();
            pdf = newOut.toByteArray();
        }
        return pdf;
    }

    private PdfPTable getHeaderTable(int x, int y) {
        PdfPTable table = new PdfPTable(1);
        table.setTotalWidth(515);
        table.setLockedWidth(true);
        table.getDefaultCell().setFixedHeight(15);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
        if (!StringUtils.isEmpty(stamp)) {
            table.addCell(new Phrase(stamp, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9)));
        }
        if (!StringUtils.isEmpty(pageHeader)) {
            table.addCell(new Phrase(pageHeader, FontFactory.getFont(FontFactory.HELVETICA, 9)));
        }
        return table;
    }

    private PdfPTable getFooterTable(int x, int y) {
        PdfPTable table = new PdfPTable((needPageNo && !StringUtils.isEmpty(pageFooter)) ? 2 : 1);
        table.setTotalWidth(515);
        table.setLockedWidth(true);
        table.getDefaultCell().setFixedHeight(20);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        if (!StringUtils.isEmpty(pageFooter)) {
            table.addCell(new Phrase(pageFooter, FontFactory.getFont(FontFactory.HELVETICA, 8)));
        }
        if (needPageNo) {
            if (!StringUtils.isEmpty(pageFooter)) {
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            } else {
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            }
            table.addCell(new Phrase(String.format("Page %d of %d", x, y), FontFactory.getFont(FontFactory.HELVETICA, 8)));
        }
        return table;
    }
}
