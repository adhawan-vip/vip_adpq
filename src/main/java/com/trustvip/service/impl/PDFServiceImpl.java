package com.trustvip.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.jsoup.Jsoup;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class PDFServiceImpl {
    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    
    public ByteArrayOutputStream createPDFfromHTML(String HTML)
            throws DocumentException, IOException
    {
        float left = 54;
        float right = 54;
        float top = 54;
        float bottom = 54;
        CharSequence target = "<br>";
        CharSequence replaceWith = " ";
        Resource resource = new ClassPathResource("CDT.pdf");
        InputStream resourceInputStream = resource.getInputStream();
        String HTML2 = HTML.replace(target, replaceWith );
        Document pdf = new Document(PageSize.A4, left, right, top, bottom);
        PdfWriter writer = PdfWriter.getInstance(pdf, byteArrayOutputStream);
        pdf.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, pdf,
                new ByteArrayInputStream(Jsoup.parse(HTML2).toString().getBytes(StandardCharsets.UTF_8)));
        pdf.close();
        
        PdfReader coverPage = new PdfReader(resourceInputStream);
        PdfReader document = new PdfReader(byteArrayOutputStream.toByteArray());
        ByteArrayOutputStream newByteArrayOutputStream = new ByteArrayOutputStream();
        Document doc = new Document();
        PdfCopy copy = new PdfCopy(doc, newByteArrayOutputStream);
        doc.open();
        copy.addDocument(coverPage);
        copy.addDocument(document);
        doc.close();
        coverPage.close();
        document.close();
        return newByteArrayOutputStream;
    }
}
