package TADS;

import TADS.entities.Node;
import atendimentobancario.model.Cliente;

public class Lista {

    private Node firstNode;
    private Node lastNode;
    private String name;
    int size;

    public Lista() {
        this("lista");
    }

    public Lista(String listName) {
        name = listName;
        firstNode = lastNode = null;
        size = 0;
    }

    public void insertInicio(Cliente insertItem) {
        Node novoNo = new Node(insertItem);
        
        if (isEmpty()) // firstNode e lastNode se referem ao mesmo objeto
        {
            firstNode = lastNode = novoNo;
        } else // firstNode se refere ao novo no
        {
            novoNo.setNext(firstNode);
            firstNode.setPrev(novoNo);
            firstNode = novoNo;
        }
        size++;
    }

    public void insertFim(Cliente insertItem) {
        Node novoNo = new Node(insertItem);
        if (isEmpty()) {
            firstNode = lastNode = novoNo;
        } else {
            lastNode.setNext(novoNo);
            novoNo.setPrev(lastNode);
            lastNode = novoNo;
        }
        size++;
    }

    public Cliente removeInicio() {
        if (isEmpty()) // gera exceção se List está vazia
        {
            return null;
        }

        Cliente removedItem = firstNode.getObject(); // retrieve data being removed

        // atualiza referências firstNode e lastNode - unico elemento da lista 
        if (firstNode == lastNode) {
            firstNode = lastNode = null;
        } else {
            firstNode.getNext().setPrev(null);
            firstNode = firstNode.getNext();
        }
        size--;
        return removedItem;
    }

    public Cliente removeFim() {
        if (isEmpty()) // lança exceção se a lista está vazia
        {
            return null;
        }

        Cliente removedItem = lastNode.getObject(); // retrieve data being removed

        // atualiza referências firstNode e lastNode - unico elemento da lista
        if (firstNode == lastNode) {
            firstNode = lastNode = null;
        } else { // localiza o novo lastNode
            lastNode.getPrev().setNext(null);
            lastNode = lastNode.getPrev();
        }
        size--;
        return removedItem; // return removed node data
    }

    public boolean isEmpty() {
        return firstNode == null;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Empty " + name);
            return;
        }

        System.out.print("The " + name + " is: ");
        Node current = firstNode;

        while (current != null) {
            System.out.print("\t" + current.getObject());
            current = current.getNext();
        }
        System.out.println("\n");
    }
    
    public int getSize(){
        return size;
    }
    
    //insere em uma posicao específica
    public void inserePosicao(int pos, Cliente valor){
        if(pos <= 0)
            System.out.println("A primeira posicao eh a 1. Inserir uma posicao existente!");
        else{
            if(pos == 1) // inserir no inicio
                insertInicio(valor);
            else{
                if(pos > size) // inserir no fim
                    insertFim(valor);
                else{
                    Node novoNo = new Node(valor);
                    // percorrer a lista para encontrar a posicao
                    Node temp = firstNode;
                    for(int i=1; i<pos; i++)
                        temp = temp.getNext();
                    temp.getPrev().setNext(novoNo);
                    novoNo.setPrev(temp.getPrev());
                    novoNo.setNext(temp);
                    temp.setPrev(novoNo);
                    
                    size++;
                }
            }
        }
    }
    
    //remove uma posicao especifica
    public Cliente removePosicao(int pos){
        if(pos <= 0){
            System.out.println("A primeira posicao eh a 1. Inserir uma posicao existente!");
            return null;
        }else{
            if(pos == 1) // remover no inicio
                return removeInicio();
            else{
                if(pos >= size) // remover no fim
                    return removeFim();
                else{
                    Cliente valorRemovido;
                    // percorrer a lista para encontrar a posicao
                    Node temp = firstNode;
                    for(int i=1; i<pos; i++)
                        temp = temp.getNext();
                    valorRemovido = temp.getObject();
                    temp.getPrev().setNext(temp.getNext());
                    temp.getNext().setPrev(temp.getPrev());
                    
                    size--;
                    return valorRemovido;
                    
                }
            }
        }
        
    }

}
