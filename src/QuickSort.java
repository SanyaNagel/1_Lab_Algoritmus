
import java.util.Comparator;

public class QuickSort {

    public Record theArray[];
    public int numberRecord;
    public boolean stringSort = false;
    public int fieldCurrent;

    public QuickSort(Record[] records, int numberRecord) {
        this.numberRecord = numberRecord;
        theArray = records;
    }

    public void quickSort(int index1, int index2, int index3) {
        fieldCurrent = index1;
        recQuickSort(0, numberRecord - 1);

        for(int i = 0; i < numberRecord; i++)
        {
            int first = i;
            while (++i < numberRecord && getComparatorByIndex(index1).compare(theArray[first], theArray[i]) == 0);
            if(first != --i){   //Если нашли последовательность записей с одинаковыми полями
                fieldCurrent = index2;
                recQuickSort(first, i);   //Сортируем пл индкусу2

                for(int j = first; j < i+1; j++)
                {
                    int first2 = j;
                    while (++j < numberRecord && getComparatorByIndex(index2).compare(theArray[first2], theArray[j]) == 0);
                    if(first2 != --j){   //Если нашли последовательность записей с одинаковыми полями
                        fieldCurrent = index3;
                        recQuickSort(first2, j);   //Сортируем пл индкусу3
                    }
                }
            }
        }
    }

    public void recQuickSort(int left, int right) {
        if (right - left <= 0) // Если размер <= 1,
            return; // массив отсортирован
        else // Для размера 2 и более
        {
            Record pivot = theArray[right]; // Крайний правый элемент
            // Разбиение диапазона
            int partition = partitionItInt(left, right, pivot);
            recQuickSort(left, partition - 1); // Сортировка левой части
            recQuickSort(partition + 1, right); // Сортировка правой части
        }
    }

    public int partitionItInt(int left, int right, Record pivot) {
        int leftPtr = left - 1; // Левая граница (после ++)
        int rightPtr = right; // Правая граница-1 (after --)
        while (true) { // Поиск большего элемента
            while (getComparatorByIndex(fieldCurrent).compare(theArray[++leftPtr], pivot) < 0)
                ; // пока левый элемент меньше pivot

            while (rightPtr > 0 && getComparatorByIndex(fieldCurrent).compare(theArray[--rightPtr], pivot) > 0)
                ; //пока правый элемент больше pivot
            if (leftPtr >= rightPtr) // Если указатели сошлись,
                break; // разбиение закончено.
            else // В противном случае
                swap(leftPtr, rightPtr); // поменять элементы местами.
        }
        swap(leftPtr, right); // Перестановка опорного элемента
        return leftPtr; // Возврат позиции опорного элемента
    }

    public void swap(int dex1, int dex2) { // Перестановка двух элементо
        Record temp = theArray[dex1]; // A копируется в temp
        theArray[dex1] = theArray[dex2]; // B копируется в A
        theArray[dex2] = temp; // temp копируется в B
    }

    /**
     * Получение способа сравнения обектов, по индексу поля
     *
     * @param index индекс поля
     * @return
     */
    public static Comparator getComparatorByIndex(int index) {
        switch (index) {
            case 1:
                return (o1, o2) -> ((Record) o1).one.compareTo(((Record) o2).one);
            case 2:
                return (o1, o2) -> ((Record) o1).two.compareTo(((Record) o2).two);
            case 3:
                return (o1, o2) -> ((Record) o1).three - ((Record) o2).three;
            case 4:
                return (o1, o2) -> ((Record) o1).four - ((Record) o2).four;
            case 5:
                return (o1, o2) -> ((Record) o1).five - ((Record) o2).five;
            default:
                return null;
        }
    }
}
