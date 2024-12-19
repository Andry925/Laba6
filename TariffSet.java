import java.util.*;


public class TariffSet<T extends Tariff> implements Set<T> {
    private class Node {
        T data;
        Node next;
        Node prev;

        Node(T data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    
    public TariffSet() {
        head = null;
        tail = null;
        size = 0;
    }


    public TariffSet(T tariff) {
        this();
        add(tariff);
    }

    
    public TariffSet(Collection<? extends T> collection) {
        this();
        for (T tariff : collection) {
            add(tariff);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null || !(o instanceof Tariff)) return false;
        Node current = head;
        while (current != null) {
            if (current.data.equals(o)) return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        Node current = head;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }

    @Override
    public <U> U[] toArray(U[] a) {
        if (a.length < size) {
            a = Arrays.copyOf(a, size);
        }
        int index = 0;
        Node current = head;
        while (current != null) {
            a[index++] = (U) current.data;
            current = current.next;
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(T tariff) {
        if (tariff == null || contains(tariff)) return false;
        Node newNode = new Node(tariff);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null || !(o instanceof Tariff)) return false;
        Node current = head;
        while (current != null) {
            if (current.data.equals(o)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }

                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for (T tariff : c) {
            modified |= add(tariff);
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Node current = head;
        while (current != null) {
            if (!c.contains(current.data)) {
                Node toRemove = current;
                current = current.next;
                remove(toRemove.data);
                modified = true;
            } else {
                current = current.next;
            }
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            modified |= remove(o);
        }
        return modified;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TariffSet{");
        Node current = head;
        while (current != null) {
            sb.append(current.data).append(current.next != null ? ", " : "");
            current = current.next;
        }
        sb.append("}");
        return sb.toString();
    }


    public static void main(String[] args) {
        TariffSet<Tariff> tariffSet = new TariffSet<>();
        tariffSet.add(new Tariff("Basic Tariff", 10.0, 100));
        tariffSet.add(new Tariff("Premium Tariff", 20.0, 200));
        tariffSet.add(new Tariff("Ultra Tariff", 30.0, 300));

        System.out.println("Initial TariffSet: " + tariffSet);

        Tariff extraTariff = new Tariff("Extra Tariff", 15.0, 150);
        tariffSet.add(extraTariff);
        System.out.println("After processing Extra Tariff: " + tariffSet);

        tariffSet.remove(extraTariff);
        System.out.println("After removing Extra Tariff: " + tariffSet);

        System.out.println("Size: " + tariffSet.size());
        System.out.println("Contains 'Premium Tariff': " + tariffSet.contains(new Tariff("Premium Tariff", 20.0, 200)));
    }
}
