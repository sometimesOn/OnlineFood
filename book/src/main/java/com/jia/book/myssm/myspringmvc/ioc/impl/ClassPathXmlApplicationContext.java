package com.jia.book.myssm.myspringmvc.ioc.impl;



import com.jia.book.myssm.myspringmvc.ioc.BeanFactory;
import com.jia.book.myssm.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ClassPathXmlApplicationContext implements BeanFactory {
    private Map<String,Object> beanMap = new HashMap<>();

    private  String path = "applicationContext.xml";

    public ClassPathXmlApplicationContext(){
        this("applicationContext.xml");
    }

    public ClassPathXmlApplicationContext(String path){
        if(StringUtil.isEmpty(path)){
            throw new RuntimeException("文件路径未找到");
        }
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            NodeList beannodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < beannodeList.getLength(); i++) {
                Node beanNode = beannodeList.item(i);
                if(beanNode.getNodeType()==Node.ELEMENT_NODE){
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    String className = beanElement.getAttribute("class");
                    Object beanObj = Class.forName(className).getDeclaredConstructor().newInstance();
                    beanMap.put(beanId,beanObj);
                }
            }
            //组装bean之间的依赖关系
            for (int i = 0; i <beannodeList.getLength() ; i++) {
                Node beanNode = beannodeList.item(i);
                if(beanNode.getNodeType()==Node.ELEMENT_NODE) {
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    NodeList beanChildNodeList = beanElement.getChildNodes();
                    for (int j = 0; j < beanChildNodeList.getLength(); j++) {
                        Node beanChildNode = beanChildNodeList.item(j);
                        if(beanChildNode.getNodeType()==Node.ELEMENT_NODE&&"property".equals(beanChildNode.getNodeName())){
                            Element propertyElement = (Element) beanChildNode;
                            String propertyName = propertyElement.getAttribute("name");
                            String propertyRef = propertyElement.getAttribute("ref");
                            //找到propertyRef对应的实例
                            Object refObj = beanMap.get(propertyRef);
                            //将refObj设置到当前bean对应的实列的proper属性上
                            Object beanObj = beanMap.get(beanId);

                            Class beanClazz = beanObj.getClass();
                            Field propertyField = beanClazz.getDeclaredField(propertyName);
                            propertyField.setAccessible(true);
                            propertyField.set(beanObj,refObj);
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getBean(String beanId) {
        return beanMap.get(beanId);
    }
}
