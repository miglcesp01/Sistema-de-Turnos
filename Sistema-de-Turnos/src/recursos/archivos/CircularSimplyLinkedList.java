/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos.archivos;

import java.util.Iterator;

/**
 *
 * @author Alexis
 */
public class CircularSimplyLinkedList<E> implements List<E>, Iterable<E>{
    private Node<E> last;
    private int current;
    public CircularSimplyLinkedList(){
        last = null;
        current = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>(){
            Node<E> p=last.next;
            public boolean hasNext(){
                return !isEmpty();
            }
            
            public E next(){
                E tmp=p.data;
                p=p.next;
                return tmp;
            }
        };
    }
    
    private class Node<E>{
        private E data;
        private Node<E> next;
        
        public Node(E data)
        {
            this.data = data;
            this.next = null;
        }        
    }
    
    @Override
    public boolean addFirst(E e) {
        if(e==null) return false;
        Node<E> n=new Node<>(e);
        if(isEmpty()){ last=n;
            last.next=n;
        }else{
            Node<E> tmp=last.next;
            last.next=n;
            n.next=tmp;
        }current++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        if(e==null) return false;
        Node<E> n= new Node<>(e);
        if(isEmpty()){ last=n;
        last.next=n;
        }else{
            Node<E> tmp=last.next;
            n.next=tmp;
            last.next=last=n;
        }current++;        
        return true;
    }

    @Override
    public E getFirst() {
        if(isEmpty()) throw new IllegalArgumentException("Se encuentra la lista vacia");
        return last.next.data;
    }

    @Override
    public E getLast() {
        if(isEmpty()) throw new IllegalArgumentException("Se encuentra la lista vacia");
        return last.data;
    }

    @Override
    public int indexOf(E e) {
        if(e==null) throw new IllegalArgumentException("El elemento es null");
        else if(isEmpty()) throw new IllegalArgumentException("La lista esta vacia");
        int i=0;
        Node<E> q=last.next;
        do{
            if(q.data.equals(e)) return i;
            i++;
            q=q.next;
        }while(q!=last.next);
        return -1;
    }

    @Override
    public int size() {
        return current;
    }

    @Override
    public boolean removeLast() {
        if(isEmpty()) return false;
        last.data=null;
        if(last.next==last)last=last.next=null;
        else{
            Node<E> n=getPrevius(last);
            n.next=last.next;
            last.next=null;
            last=n;
        }current--;
        return true;
    }
    
    private Node<E> getPrevius(Node<E> p){
        Node<E> q=last.next;
        do{
            if(q.next==p) return q;
            q=q.next;
        }while(q!=last.next);
        return null;
    }

    @Override
    public boolean removeFirst() {
        if(isEmpty()) return false;
        last.next.data=null;
        if(last.next==null)last=null;
        else{
            Node<E> tmp=last.next;
            last.next=tmp.next;
            tmp.next=null;
        }current--;
        return true;
        
    }

    @Override
    public boolean insert(int index, E e) {
        if(e==null || index<0 || index>current) return false;
        else if(index==0) return addFirst(e);
        else if(index==current) return addLast(e);
        Node<E> n=new Node<>(e);
        Node<E> q=searchNode(--index);
        n.next=q.next;
        q.next=n;
        current++;
        return true;
    }
    
    private Node<E> searchNode(int index){
        if(index<0 || current>=current) return null;//para que no vueltas el for del principio por algún error
        if(index==current-1) return last;
        Node<E> p=last.next;
        for(int i=0;i<index;i++) 
            p=p.next;
        return p;
    }

    @Override
    public boolean set(int index, E e) {
        if(e==null || index<0 || index>=current) return false;
        Node<E> n=searchNode(index);
        n.data=e;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return last == null;
    }

    @Override
    public E get(int index) {
        if(index<0 || index>=current) throw new IllegalArgumentException("El indice esta fuera de rango");
        return searchNode(index).data;
    }

    @Override
    public boolean contains(E e) {
        Node<E> p = last.next;
        do{
            if(p.data.equals(e))
                return true;
            p=p.next;
        }while(p!=last.next);
        return false;
    }

    @Override
    public boolean remove(int index) {
        if(index<0 || index>=current) return false;
        else if(index==0) return removeFirst();
        else if(index==current-1) return removeLast();
        Node<E> q=searchNode(--index);
        q.next=q.next.next;
        q.next.data=null;
        q.next.next=null;
        current--;
        return true;
    }
    
    @Override
     public String toString(){
        if(isEmpty()) return "[]";
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        for(Node<E> p=last.next; p!=last; p=p.next){
            sb.append(p.data);
            sb.append(",");
        }sb.append(last.data);
        sb.append("]");
        return sb.toString();
    }
}
