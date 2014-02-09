package org.bbelovic.codekatas;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;

public class TriangleSerializer {

    public void serialize(final Triangle triangle, final String fileName) {
        final Path destination = Paths.get(fileName);
        try(final BufferedWriter bw = Files.newBufferedWriter(destination, UTF_8)) {
           final String triangleString = serialize(triangle);
           bw.write(triangleString);
           bw.flush();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public Triangle deserialize(final String fileName) throws IOException {
        final Path inputPath = Paths.get(fileName);
        final List<String> inputData = Files.readAllLines(inputPath, UTF_8);
        if (inputData.isEmpty()) {
            throw new RuntimeException("Empty input file");
        }
        final int [][] arr = new int [inputData.size()][];
        int x = 0;
        for (final String eachLine: inputData) {
            final String [] values = eachLine.trim().split(" ");
            int [] ints = new int [values.length];
            for (int i = 0; i < values.length; i++) {
                ints [i] = Integer.parseInt(values[i]);
            }
            arr [x++] = ints;
        }
        return new Triangle(arr);
    }

    private static String serialize(final Triangle triangle) {
        final int size = triangle.getSize();
        int rowLength = 1;
        final StringBuilder result = new StringBuilder();
        final String spaces = generateSpaces(size - 1);
        for (int i = 0; i < size; i++) {
            result.append(spaces.substring(i));
            final StringBuilder rowData = new StringBuilder();
            for (int j = 0; j < rowLength; j++) {
                int num = triangle.getElementAtPosition(i, j);
                rowData.append(num);
                rowData.append(" ");
            }
            result.append(format("%s%n", rowData.toString().trim()));
            rowLength++;
        }
        return result.toString();
    }

    private static String generateSpaces(final int length) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
