import java.util.ArrayList;

public class StudentManagement {
    private ArrayList<Student> studentList = new ArrayList<>();

    public void addStudent(Student student) {
        studentList.add(student);
        updateRank();
    }

    public void editStudent(int id, String newFullName, double newMarks) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                student.setFullName(newFullName);
                student.setMarks(newMarks);
                updateRank();
                return;
            }
        }
        System.out.println("Student with ID: " + id + " not found.");
    }

    public void deleteStudent(int id) {
        studentList.removeIf(student -> student.getId() == id);
        updateRank();
    }

    public void searchStudent(int id) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student with ID: " + id + " not found.");
    }

    // Sorting students with Quick Sort
    public void sortStudents(String sortType) {
        if (sortType.equalsIgnoreCase("quick")) {
            quickSort(0, studentList.size() - 1);
        } else {
            System.out.println("Invalid choice. Use 'quick'.");
        }
        updateRank();
    }

    // Quick Sort
    private void quickSort(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);
            quickSort(low, pivotIndex - 1);
            quickSort(pivotIndex + 1, high);
        }
    }

    private int partition(int low, int high) {
        double pivot = studentList.get(high).getMarks();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (studentList.get(j).getMarks() > pivot) { // Descending order
                i++;
                // Swap
                Student temp = studentList.get(i);
                studentList.set(i, studentList.get(j));
                studentList.set(j, temp);
            }
        }
        // Swap with pivot
        Student temp = studentList.get(i + 1);
        studentList.set(i + 1, studentList.get(high));
        studentList.set(high, temp);
        return i + 1;
    }

    public void displayStudentList() {
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    private void updateRank() {
        for (int i = 0; i < studentList.size(); i++) {
            studentList.get(i).setRank(i + 1);
        }
    }
}
