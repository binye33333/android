package com.chenyoyo.adaption;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by chenyo on 2016/1/12.
 * description：
 */
public class DimensParser {

    private List<DimenValues> list = new ArrayList<>();

    public List<DimenValues> parse(InputStream inputStream) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        SAXParser parser = parserFactory.newSAXParser();
        parser.parse(inputStream, new InnerHandler());
        return list;
    }

    private class InnerHandler extends DefaultHandler {
        private DimenValues dimenValues;
        private StringBuilder stringBuilder = new StringBuilder();
        private String tempName;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("dimen")) {
                tempName = qName;
                dimenValues = new DimenValues();
                stringBuilder.setLength(0);
                dimenValues.name = attributes.getValue("name");
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("dimen")) {
                list.add(dimenValues);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            stringBuilder.append(ch, start, length);
            if (tempName != null && tempName.equals("dimen")) {
                dimenValues.value = stringBuilder.toString();
            }
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }
    }


}
