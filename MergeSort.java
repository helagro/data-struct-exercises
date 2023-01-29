public class MergeSort {

    public static int[] merge(int[] arr1, int[] arr2){
        final int[] res = new int[arr1.length + arr2.length];

        int arr1I, arr2I, resI;
        arr1I = arr2I = resI = 0;

        while(arr1I < arr1.length && arr2I < arr2.length){
            if(arr1[arr1I] > arr2[arr2I]){
                res[resI] = arr2[arr2I];
                arr2I++;
            } else {
                res[resI] = arr1[arr1I];
                arr1I++;
            }
            resI++;
        }

        for(; arr1I < arr1.length; arr1I++){
            res[resI] = arr1[arr1I];
            resI++;
        }

        for(; arr2I < arr2.length; arr2I++){
            res[resI] = arr2[arr2I];
            resI++;
        }

        return res;
    }


    public static void main(String[] args) {
        final int[] arr1 = {1, 3, 5, 6, 9};
        final int[] arr2 = {4, 6, 7, 9, 10, 34, 100};

        final int[] res = merge(arr1, arr2);
        for(final int resElem : res){
            System.out.printf("%d, ", resElem);
        }
        System.out.println();
    }
}
