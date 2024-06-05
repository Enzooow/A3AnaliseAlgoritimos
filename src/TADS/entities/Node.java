/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TADS.entities;

import atendimentobancario.model.Cliente;

public class Node {

    private Cliente cliente;
    private Node nextNode;
    private Node prevNode;

    public Node(Cliente object) {
        this(object, null, null);
    }

    public Node(Cliente object, Node next, Node prev) {
        cliente = object;
        nextNode = next;
        prevNode = prev;
    }

    public Cliente getObject() {
        return cliente; // return Object in this node
    }

    public Node getNext() {
        return nextNode;
    }
    
    public Node getPrev() {
        return prevNode;
    }

    public void setObject(Cliente object) {
        cliente = object;
    }

    public void setNext(Node next) {
        nextNode = next;
    }

    public void setPrev(Node prev) {
        prevNode = prev;
    }

}
