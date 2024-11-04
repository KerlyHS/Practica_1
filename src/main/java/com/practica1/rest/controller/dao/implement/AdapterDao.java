package com.practica1.rest.controller.dao.implement;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import com.practica1.rest.controller.tda.list.LinkedList;
import com.practica1.rest.controller.tda.Arreglos.tdaArray;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AdapterDao<T> implements InterfazDao<T> {
    private Class<T> clazz;
    private Gson g;
    // Nueva ruta para almacenar los archivos JSON
    public static String URL = "C:/Users/Usuario/Desktop/Practica_U1/Practica1/media/";
    private boolean useLinkedList;

    // Constructor que permite elegir entre LinkedList y tdaArray
    public AdapterDao(Class<T> clazz, boolean useLinkedList) {
        this.clazz = clazz;
        this.useLinkedList = useLinkedList;
        g = new Gson();
    }

    @Override
    public LinkedList<T> listAll() {
        LinkedList<T> list = new LinkedList<>();
        try {
            String data = readFile();
            T[] matrix = g.fromJson(data, TypeToken.getArray(clazz).getType());

            if (useLinkedList) {
                list.toList(matrix); // Convertir array a LinkedList
                return list;
            } else {
                tdaArray<T> array = new tdaArray<>();
                for (T item : matrix) {
                    array.add(item); // Agregar elementos al tdaArray
                }
                list.toList(array.toArray()); // Convertir a LinkedList para cumplir con el tipo de retorno
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public T get(Integer id) throws Exception {
        // Implementación del método get si es necesario
        return null;
    }

    @Override
    public void persist(T object) throws Exception {
        LinkedList<T> list = listAll();
        list.add(object);
        String info = g.toJson(list.toArray());
        saveFile(info);
    }

    @Override
    public void merge(T object, Integer index) throws Exception {
        // Implementar lógica de modificación para ambos tipos
        LinkedList<T> list = listAll();
        list.set(object, index);
        String info = g.toJson(list.toArray());
        saveFile(info);
    }

    @Override
    public void delete(Integer id) throws Exception {
        LinkedList<T> list = listAll();
        list.removeAt(id.intValue());
        String info = g.toJson(list.toArray());
        saveFile(info);
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
