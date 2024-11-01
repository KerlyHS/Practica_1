package com.practica1.rest.controller.dao.implement;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import com.practica1.rest.controller.tda.list.LinkedList;
import com.practica1.rest.controller.tda.arreglos.tdaArray; // Importar tdaArray
import com.google.gson.Gson;

public class AdapterDao<T> implements InterfazDao<T> {
    private Class<T> clazz;
    private Gson g;
    public static String URL = "media/";
    private boolean useLinkedList; // Variable para elegir entre LinkedList y tdaArray

    // Constructor que permite elegir entre LinkedList y tdaArray
    public AdapterDao(Class<T> clazz, boolean useLinkedList) {
        this.clazz = clazz;
        this.useLinkedList = useLinkedList; // Establecer si se usa LinkedList o tdaArray
        g = new Gson();
    }

    @Override
    public Object listAll() { // Cambia el tipo de retorno a Object
        if (useLinkedList) {
            LinkedList<T> list = new LinkedList<>();
            try {
                String data = readFile();
                T[] matrix = (T[]) g.fromJson(data, java.lang.reflect.Array.newInstance(clazz, 0).getClass());
                list.toList(matrix);
                return list; // Retorna la lista enlazada
            } catch (Exception e) {
                e.printStackTrace(); // Manejo de excepciones
            }
            return list;
        } else {
            tdaArray<T> array = new tdaArray<>();
            try {
                String data = readFile();
                T[] matrix = (T[]) g.fromJson(data, java.lang.reflect.Array.newInstance(clazz, 0).getClass());
                for (T item : matrix) {
                    array.add(item); // Agrega los elementos al arreglo
                }
                return array; // Retorna el tdaArray
            } catch (Exception e) {
                e.printStackTrace(); // Manejo de excepciones
            }
            return array;
        }
    }

    public T get(Integer id) throws Exception {
        return null;
    }

    public void persist(T object) throws Exception {
        if (useLinkedList) {
            LinkedList<T> list = (LinkedList<T>) listAll(); // Castear a LinkedList
            list.add(object);
            String info = g.toJson(list.toArray());
            saveFile(info);
        } else {
            tdaArray<T> array = (tdaArray<T>) listAll(); // Castear a tdaArray
            array.add(object);
            String info = g.toJson(array.toArray());
            saveFile(info);
        }
    }

    public void merge(T object, Integer index) throws Exception {
        // Implementar lógica de modificación para ambos tipos
    }

    private String readFile() throws Exception {
        Scanner in = new Scanner(new FileReader(URL + clazz.getSimpleName() + ".json"));
        StringBuilder sb = new StringBuilder();
        while (in.hasNext()) {
            sb.append(in.next());
        }
        in.close();
        return sb.toString();
    }

    private void saveFile(String data) throws Exception {
        FileWriter f = new FileWriter(URL + clazz.getSimpleName() + ".json");
        f.write(data);
        f.flush();
        f.close();
    }
}
