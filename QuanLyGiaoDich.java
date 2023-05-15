import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class GiaoDich {
    protected String maGiaoDich;
    protected String ngayGiaoDich;
    protected double donGia;
    protected double dienTich;

    public GiaoDich(String maGiaoDich, String ngayGiaoDich, double donGia, double dienTich) {
        this.maGiaoDich = maGiaoDich;
        this.ngayGiaoDich = ngayGiaoDich;
        this.donGia = donGia;
        this.dienTich = dienTich;
    }

    public abstract double tinhThanhTien();

    public String getMaGiaoDich() {
        return maGiaoDich;
    }

    public String getNgayGiaoDich() {
        return ngayGiaoDich;
    }

    public double getDonGia() {
        return donGia;
    }

    public double getDienTich() {
        return dienTich;
    }
}

class GiaoDichDat extends GiaoDich {
    private char loaiDat;

    public GiaoDichDat(String maGiaoDich, String ngayGiaoDich, double donGia, double dienTich, char loaiDat) {
        super(maGiaoDich, ngayGiaoDich, donGia, dienTich);
        this.loaiDat = loaiDat;
    }

    @Override
    public double tinhThanhTien() {
        if (loaiDat == 'A') {
            return (dienTich * donGia * 1.5);
        } else {
            return (dienTich * donGia);
        }
    }

    public char getLoaiDat() {
        return loaiDat;
    }
}

class GiaoDichNha extends GiaoDich {
    private String loaiNha;
    private String diaChi;

    public GiaoDichNha(String maGiaoDich, String ngayGiaoDich, double donGia, double dienTich, String loaiNha, String diaChi) {
        super(maGiaoDich, ngayGiaoDich, donGia, dienTich);
        this.loaiNha = loaiNha;
        this.diaChi = diaChi;
    }

    @Override
    public double tinhThanhTien() {
        if (loaiNha.equals("cao cap")) {
            return (dienTich * donGia);
        } else {
            return (dienTich * donGia * 0.9);
        }
    }

    public String getLoaiNha() {
        return loaiNha;
    }

    public String getDiaChi() {
        return diaChi;
    }
}

public class QuanLyGiaoDich {
    private List<GiaoDich> danhSachGiaoDich;

    public QuanLyGiaoDich() {
        danhSachGiaoDich = new ArrayList<>();
    }

    public void themGiaoDich(GiaoDich giaoDich) {
        danhSachGiaoDich.add(giaoDich);
    }

    public void nhapDanhSachGiaoDich() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Nhap so luong giao dich: ");
            int soLuong = scanner.nextInt();

            for (int i = 0; i < soLuong; i++) {
                System.out.println("Nhap thong tin giao dich thu " + (i + 1));
                System.out.print("Loai giao dich (1 - Dat, 2 - Nha): ");
                int loaiGiaoDich = scanner.nextInt();

                System.out.print("Ma giao dich: ");
                String maGiaoDich = scanner.next();

                System.out.print("Ngay giao dich (dd/mm/yyyy): ");
                String ngayGiaoDich = scanner.next();

                System.out.print("Don gia: ");
                double donGia = scanner.nextDouble();

                System.out.print("Dien tich: ");
                double dienTich = scanner.nextDouble();

                if (loaiGiaoDich == 1) {
                    System.out.print("Loai dat (A, B, C): ");
                    char loaiDat = scanner.next().charAt(0);

                    GiaoDichDat giaoDichDat = new GiaoDichDat(maGiaoDich, ngayGiaoDich, donGia, dienTich, loaiDat);
                    themGiaoDich(giaoDichDat);
                } else if (loaiGiaoDich == 2) {
                    System.out.print("Loai nha (cao cap, thuong): ");
                    String loaiNha = scanner.next();

                    System.out.print("Dia chi: ");
                    String diaChi = scanner.next();

                    GiaoDichNha giaoDichNha = new GiaoDichNha(maGiaoDich, ngayGiaoDich, donGia, dienTich, loaiNha, diaChi);
                    themGiaoDich(giaoDichNha);
                } else {
                    System.out.println("Loai giao dich khong hop le");
                    i--; // Nhap lai thong tin giao dich hien tai
                }
            }
        }
    }

    public void xuatDanhSachGiaoDich() {
        System.out.println("Danh sach giao dich:");
        for (GiaoDich giaoDich : danhSachGiaoDich) {
            if (giaoDich instanceof GiaoDichDat) {
                GiaoDichDat giaoDichDat = (GiaoDichDat) giaoDich;
                System.out.println("Loai giao dich: Dat");
                System.out.println("Ma giao dich: " + giaoDichDat.getMaGiaoDich());
                System.out.println("Ngay giao dich: " + giaoDichDat.getNgayGiaoDich());
                System.out.println("Don gia: " + giaoDichDat.getDonGia());
                System.out.println("Dien tich: " + giaoDichDat.getDienTich());
                System.out.println("Loai dat: " + giaoDichDat.getLoaiDat());
                System.out.println("Thanh tien: " + giaoDichDat.tinhThanhTien());
                System.out.println("----------------------");
            } else if (giaoDich instanceof GiaoDichNha) {
                GiaoDichNha giaoDichNha = (GiaoDichNha) giaoDich;
                System.out.println("Loai giao dich: Nha");
                System.out.println("Ma giao dich: " + giaoDichNha.getMaGiaoDich());
                System.out.println("Ngay giao dich: " + giaoDichNha.getNgayGiaoDich());
                System.out.println("Don gia: " + giaoDichNha.getDonGia());
                System.out.println("Dien tich: " + giaoDichNha.getDienTich());
                System.out.println("Loai nha: " + giaoDichNha.getLoaiNha());
                System.out.println("Dia chi: " + giaoDichNha.getDiaChi());
                System.out.println("Thanh tien: " + giaoDichNha.tinhThanhTien());
                System.out.println("----------------------");
            }
        }
    }

    public int tinhTongSoLuongLoaiGiaoDich(String loai) {
        int count = 0;
        for (GiaoDich giaoDich : danhSachGiaoDich) {
            if (giaoDich instanceof GiaoDichDat) {
                GiaoDichDat giaoDichDat = (GiaoDichDat) giaoDich;
                if (giaoDichDat.getLoaiDat() == loai.charAt(0)) {
                    count++;
                }
            } else if (giaoDich instanceof GiaoDichNha) {
                GiaoDichNha giaoDichNha = (GiaoDichNha) giaoDich;
                if (giaoDichNha.getLoaiNha().equals(loai)) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public double tinhTrungBinhThanhTienDat() {
        double tongThanhTien = 0;
        int count = 0;
        for (GiaoDich giaoDich : danhSachGiaoDich) {
            if (giaoDich instanceof GiaoDichDat) {
                GiaoDichDat giaoDichDat = (GiaoDichDat) giaoDich;
                tongThanhTien += giaoDichDat.tinhThanhTien();
                count++;
            }
        }
        if (count != 0) {
            return tongThanhTien / count;
        } else {
            return 0;
        }
    }
    
    public static void main(String[] args) {
        QuanLyGiaoDich quanLyGiaoDich = new QuanLyGiaoDich();
        quanLyGiaoDich.nhapDanhSachGiaoDich();
        quanLyGiaoDich.xuatDanhSachGiaoDich();
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap loai giao dich muon tinh tong: ");
        String loaiGiaoDich = scanner.next();
        int tongSoLuong = quanLyGiaoDich.tinhTongSoLuongLoaiGiaoDich(loaiGiaoDich);
        System.out.println("Tong so luong giao dich loai " + loaiGiaoDich + ": " + tongSoLuong);
        
        double trungBinhThanhTienDat = quanLyGiaoDich.tinhTrungBinhThanhTienDat();
        System.out.println("Trung binh thanh tien dat: " + trungBinhThanhTienDat);
    }
}

