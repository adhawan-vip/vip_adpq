package com.trustvip.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.jsoup.Jsoup;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class PDFServiceImpl {
    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    
    public ByteArrayOutputStream createPDFfromHTML(String HTML)
            throws DocumentException, IOException
    {
        float left = 54;
        float right = 0;
        float top = 54;
        float bottom = 54;
        CharSequence target = "<br>";
        CharSequence replaceWith = " ";
        
        String HTML2 = HTML.replace(target, replaceWith );
        Document pdf = new Document(PageSize.A4, left, right, top, bottom);
        PdfWriter writer = PdfWriter.getInstance(pdf, byteArrayOutputStream);
        pdf.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, pdf,
                new ByteArrayInputStream(Jsoup.parse(HTML2).toString().getBytes(StandardCharsets.UTF_8)));
        pdf.close();
        return byteArrayOutputStream;
    }
}
