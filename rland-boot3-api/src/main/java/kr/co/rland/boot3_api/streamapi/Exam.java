package kr.co.rland.boot3_api.streamapi;

public class Exam implements Comparable<Exam> {
    private int kor;
    private int eng;
    private int mat;

    public Exam(int kor, int eng, int mat) {
        this.kor = kor;
        this.eng = eng;
        this.mat = mat;

    }

    public Exam(int kor) {
        this.kor = kor;
    }

    public int getKor() {
        return kor;
    }

    public void setKor(int kor) {
        this.kor = kor;
    }

    public int getEng() {
        return eng;
    }

    public void setEng(int eng) {
        this.eng = eng;
    }

    public int getMat() {
        return mat;
    }

    public void setMat(int mat) {
        this.mat = mat;
    }
    public int total(){
        return kor + eng + mat;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "kor=" + kor +
                ", eng=" + eng +
                ", mat=" + mat +
                '}';
    }

    @Override
    public int compareTo(Exam exam) {
        return this.total() - exam.total();
    }

    public static Exam of(String line){
        String[] parts = line.split(",");
        return new Exam(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }
}
