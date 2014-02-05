package org.bbelovic.codekatas;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
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
        final StringBuilder sb = new StringBuilder();
        final int[][] triangleData = triangle.getTriangleData();
        final String spaces = generateSpaces(triangleData.length-1);
        for (int i = 0; i < triangleData.length; i++) {
            sb.append(spaces.substring(i))
                    .append(format(triangleLineAsString(triangleData[i]) + "%n"));
        }
        return sb.toString();
    }

    private static String triangleLineAsString(final int[] triangleLine) {
        final String valueString = Arrays.toString(triangleLine);
        return valueString.substring(1, valueString.length() - 1).replace(",", "");
    }

    private static String generateSpaces(final int length) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
