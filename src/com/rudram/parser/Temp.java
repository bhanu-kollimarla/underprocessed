// package com.rudram.parser;
//
// import javax.xml.stream.XMLOutputFactory;
// import javax.xml.stream.XMLStreamException;
// import javax.xml.stream.XMLStreamWriter;
//
// import de.odysseus.staxon.json.JsonXMLOutputFactory;
//
// public class Temp {
//
// public static void main(String[] args) {
//
// XMLOutputFactory factory = new JsonXMLOutputFactory();
// XMLStreamWriter writer = null;
//
// writer = factory.createXMLStreamWriter(System.out);
//
// writer.writeStartDocument();
// writer.writeStartElement("customer");
// writer.writeStartElement("name");
// writer.writeCharacters("John Doe");
// writer.writeEndElement();
// writer.writeStartElement("phone");
// writer.writeCharacters("555-1111");
// writer.writeEndElement();
// writer.writeEndElement();
// writer.writeEndDocument();
// writer.close();
//
// }
//
// }
