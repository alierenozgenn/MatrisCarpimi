public class MatrisCarpimi {
    
    // Matrislerin boyutu (5x5)
    static final int BOYUT = 5;

    // A matrisi
    static int[][] A = {
        {1, 2, 3, 4, 5},
        {6, 7, 8, 9, 10},
        {11, 12, 13, 14, 15},
        {16, 17, 18, 19, 20},
        {21, 22, 23, 24, 25}
    };

    // B matrisi
    static int[][] B = {
        {1, 2, 3, 4, 5},
        {6, 7, 8, 9, 10},
        {11, 12, 13, 14, 15},
        {16, 17, 18, 19, 20},
        {21, 22, 23, 24, 25}
    };

    // Sonuç matrisi C (5x5)
    static int[][] C = new int[BOYUT][BOYUT];

    public static void main(String[] args) {
        // Her bir satır için bir iş parçacığı (thread) dizisi
        Thread[] threads = new Thread[BOYUT];

        // Her bir satır için iş parçacıklarını başlatıyoruz
        for (int i = 0; i < BOYUT; i++) {
            final int satir = i;
            threads[i] = new Thread(() -> {
                // C matrisinin belirli bir satırındaki her bir elemanı hesapla
                for (int j = 0; j < BOYUT; j++) {
                    C[satir][j] = 0; // Başlangıç değeri
                    // Matris çarpımı işlemi
                    for (int k = 0; k < BOYUT; k++) {
                        C[satir][j] += A[satir][k] * B[k][j];
                    }
                }
            });
            threads[i].start(); // İş parçacığını başlat
        }

        // Ana iş parçacığı, tüm iş parçacıklarının bitmesini bekler
        for (int i = 0; i < BOYUT; i++) {
            try {
                threads[i].join(); // İlgili iş parçacığının bitmesini bekler
            } catch (InterruptedException e) {
                e.printStackTrace(); // Hata durumunda hata ayıklama çıktısı
            }
        }

        // Sonuç matrisini yazdır
        matrisYazdir(C);
    }

    // Matris yazdırma işlevi
    public static void matrisYazdir(int[][] matris) {
        for (int i = 0; i < matris.length; i++) {
            for (int j = 0; j < matris[i].length; j++) {
                System.out.print(matris[i][j] + " "); // Elemanları boşlukla ayırarak yazdır
            }
            System.out.println(); // Her satırdan sonra yeni satıra geç
        }
    }
}
