## Proyecto de Ordenamiento de Datos

Este proyecto consiste en una implementación en Java para el ordenamiento de 10 millones de datos que se encuentran en un archivo txt. El objetivo es demostrar cómo con algun método de ordenamiento puede ser utilizado para ordenar cualquier tipo de datos.

## ¿Cómo funciona QuickSort?

QuickSort es un algoritmo de ordenamiento basado en comparación que utiliza la técnica de divide y vencerás. El algoritmo se basa en tomar un elemento del arreglo como pivote y particionar el arreglo alrededor del pivote, de manera que los elementos a su izquierda sean menores que el pivote y los elementos a su derecha sean mayores. Luego, se aplica el mismo proceso recursivamente a cada subarreglo generado hasta que el arreglo completo esté ordenado.

El algoritmo QuickSort es eficiente en la mayoría de los casos y tiene una complejidad promedio de O(n log n). Sin embargo, en el peor caso puede tener una complejidad de O(n^2), lo que lo hace menos eficiente que otros algoritmos de ordenamiento como MergeSort.

## ¿Cómo funciona ShellSort?

ShellSort es un algoritmo de ordenamiento que se basa en la técnica de inserción directa, pero en lugar de trabajar sobre todo el arreglo, se realiza una serie de inserciones sobre subarreglos que están separados por una brecha (gap) en lugar de realizar inserciones sobre el arreglo completo. La brecha va disminuyendo en cada iteración hasta llegar a 1, que es el método de ordenamiento por inserción tradicional.

El algoritmo ShellSort es eficiente en la mayoría de los casos y tiene una complejidad promedio de O(n log n). Además, su implementación es relativamente sencilla y no requiere de memoria adicional para almacenar los subarreglos, lo que lo hace útil para ordenar grandes conjuntos de datos en situaciones en las que la memoria es limitada.

En resumen, QuickSort es un algoritmo de ordenamiento basado en comparación que utiliza la técnica de divide y vencerás, mientras que ShellSort es un algoritmo de ordenamiento que se basa en la técnica de inserción directa y que utiliza subarreglos separados por una brecha. Ambos algoritmos son eficientes y útiles en diferentes situaciones, por lo que es importante conocerlos y saber cuándo utilizar cada uno de ellos.

## ¿Cómo utilizar este proyecto?

Para utilizar este proyecto, sigue los siguientes pasos:

    Clona este repositorio en tu computadora utilizando el comando git clone.
    Abre el proyecto en tu IDE preferido (por ejemplo, VS Code).
    Ejecuta el archivo Main.java para probar el método de ordenamiento MergeSort.

## Créditos

Este proyecto fue desarrollado por Jhoan Aristizabal, Natalia Buitrago, Wilder Sanchez para fines educativos. Siéntete libre de utilizarlo y modificarlo según tus necesidades.

## Licencia

Completamente gratuito.
