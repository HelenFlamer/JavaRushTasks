package com.javarush.task.task37.task3707;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Напиши свою реализацию метода Object clone(), сделай поверхностное клонирование.
 * <p>
 * Клонируй множество, клонируй map.
 * В случае возникновения исключений выбрось InternalError.
 * Убери лишнее пробрасывание исключения.
 * <p>
 * Расширь модификатор доступа до public.
 */
public class AmigoSet<E> extends AbstractSet implements Serializable, Cloneable, Set {
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        this.map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        this.map = new HashMap<>(Math.max(16, (int) (collection.size() / .75f + 1)));
        super.addAll(collection);
    }

    @Override
    public Object clone() {
        try {
            map.clone();
            return super.clone();
        } catch (Exception e) {
            throw new InternalError();
        }
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean add(Object e) {
        if (!map.containsKey(e)) {
            map.put((E) e, PRESENT);
            return true;
        } else {
            map.put((E) e, PRESENT);
            return false;
        }
    }

    @Override
    public Iterator iterator() {
        Set<E> keys = map.keySet();
        return keys.iterator();
    }

    @Override
    public void forEach(Consumer action) {

    }

    @Override
    public boolean removeIf(Predicate filter) {
        return false;
    }

    @Override
    public Spliterator spliterator() {
        return null;
    }

    @Override
    public Stream stream() {
        return null;
    }

    @Override
    public Stream parallelStream() {
        return null;
    }

    @Override
    public int size() {
        return map.size();
    }
}
