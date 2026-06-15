// Main.java - Entry point of the application
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Initialize the database with sample data
        Database.initializeData();
        
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Launch the home page
        SwingUtilities.invokeLater(() -> {
            new HomePage().setVisible(true);
        });
    }
}

// ==================== MODEL CLASSES ====================

// Student.java - Model class for Student
class Student {
    private String username;
    private String password;
    private String name;
    private String department;
    
    public Student(String username, String password, String name, String department) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.department = department;
    }
    
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
}

// Company.java - Model class for Company
class Company {
    private String name;
    private String location;
    
    public Company(String name, String location) {
        this.name = name;
        this.location = location;
    }
    
    public String getName() { return name; }
    public String getLocation() { return location; }
    
    @Override
    public String toString() {
        return name + " (" + location + ")";
    }
}

// Application.java - Model class for Internship Application
class Application {
    private String studentUsername;
    private String companyName;
    private String studentName;
    private String department;
    private String fromDate;
    private String toDate;
    private String resumePath;
    private String status; // Pending, Approved, Completed
    private String mentor; // Not Assigned or mentor name
    
    public Application(String studentUsername, String companyName, String studentName, 
                      String department, String fromDate, String toDate, String resumePath) {
        this.studentUsername = studentUsername;
        this.companyName = companyName;
        this.studentName = studentName;
        this.department = department;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.resumePath = resumePath;
        this.status = "Pending";
        this.mentor = "Not Assigned";
    }
    
    // Getters
    public String getStudentUsername() { return studentUsername; }
    public String getCompanyName() { return companyName; }
    public String getStudentName() { return studentName; }
    public String getDepartment() { return department; }
    public String getFromDate() { return fromDate; }
    public String getToDate() { return toDate; }
    public String getResumePath() { return resumePath; }
    public String getStatus() { return status; }
    public String getMentor() { return mentor; }
    
    // Setters
    public void setStatus(String status) { this.status = status; }
    public void setMentor(String mentor) { this.mentor = mentor; }
}

// ==================== DATABASE CLASS ====================

// Database.java - Static ArrayList storage for all data

class Database {
    // Static ArrayLists to store all data
    public static ArrayList<Student> students = new ArrayList<>();
    public static ArrayList<Company> companies = new ArrayList<>();
    public static ArrayList<Application> applications = new ArrayList<>();
    
    // Initialize sample data
    public static void initializeData() {
        // Add sample students
        students.add(new Student("suriya", "pass123", "suriya", "Computer Science"));
        students.add(new Student("ram", "pass123", "ram", "Information Technology"));
        students.add(new Student("sam", "pass123", "sam41", "Electronics"));
        
        // Add 10 companies
        companies.add(new Company("Google", "Mountain View, CA"));
        companies.add(new Company("Microsoft", "Redmond, WA"));
        companies.add(new Company("Amazon", "Seattle, WA"));
        companies.add(new Company("Apple", "Cupertino, CA"));
        companies.add(new Company("Facebook", "Menlo Park, CA"));
        companies.add(new Company("IBM", "Armonk, NY"));
        companies.add(new Company("Oracle", "Austin, TX"));
        companies.add(new Company("Intel", "Santa Clara, CA"));
        companies.add(new Company("Adobe", "San Jose, CA"));
        companies.add(new Company("Salesforce", "San Francisco, CA"));
    }
    
    // Authenticate student
    public static Student authenticateStudent(String username, String password) {
        for (Student student : students) {
            if (student.getUsername().equals(username) && 
                student.getPassword().equals(password)) {
                return student;
            }
        }
        return null;
    }
    
    // Get applications for a specific student
    public static ArrayList<Application> getApplicationsByStudent(String username) {
        ArrayList<Application> studentApps = new ArrayList<>();
        for (Application app : applications) {
            if (app.getStudentUsername().equals(username)) {
                studentApps.add(app);
            }
        }
        return studentApps;
    }
    
    // Get completed internships for a student
    public static ArrayList<Application> getCompletedInternships(String username) {
        ArrayList<Application> completed = new ArrayList<>();
        for (Application app : applications) {
            if (app.getStudentUsername().equals(username) && 
                app.getStatus().equals("Completed")) {
                completed.add(app);
            }
        }
        return completed;
    }
}

// ==================== GUI CLASSES ====================

// HomePage.java - Home page with Student and Admin login buttons

class HomePage extends JFrame {
    public HomePage() {
        setTitle("Internship Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 248, 255));
        
        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("Internship Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.BLACK);
        titlePanel.add(titleLabel);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        
        // Student Login button
        JButton studentLoginBtn = new JButton("Student Login");
        studentLoginBtn.setFont(new Font("Arial", Font.BOLD, 18));
        studentLoginBtn.setPreferredSize(new Dimension(200, 60));
        studentLoginBtn.setBackground(new Color(60, 179, 113));
        studentLoginBtn.setForeground(Color.BLACK);
        studentLoginBtn.setFocusPainted(false);
        studentLoginBtn.addActionListener(e -> {
            new StudentLoginPage().setVisible(true);
            dispose();
        });
        
        // Admin Login button
        JButton adminLoginBtn = new JButton("Admin Login");
        adminLoginBtn.setFont(new Font("Arial", Font.BOLD, 18));
        adminLoginBtn.setPreferredSize(new Dimension(200, 60));
        adminLoginBtn.setBackground(new Color(220, 20, 60));
        adminLoginBtn.setForeground(Color.BLACK);
        adminLoginBtn.setFocusPainted(false);
        adminLoginBtn.addActionListener(e -> {
            new AdminLoginPage().setVisible(true);
            dispose();
        });
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(studentLoginBtn, gbc);
        
        gbc.gridy = 1;
        buttonPanel.add(adminLoginBtn, gbc);
        
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        
        add(mainPanel);
    }
}

// StudentLoginPage.java - Student login page

class StudentLoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    
    public StudentLoginPage() {
        setTitle("Student Login");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 248, 255));
        
        // Title
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(60, 179, 113));
        JLabel titleLabel = new JLabel("Student Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titlePanel.add(titleLabel);
        
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(usernameLabel, gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(usernameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(passwordLabel, gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(passwordField, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 248, 255));
        
        JButton loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 16));
        loginBtn.setPreferredSize(new Dimension(120, 40));
        loginBtn.setBackground(new Color(60, 179, 113));
        loginBtn.setForeground(Color.BLACK);
        loginBtn.addActionListener(e -> login());
        
        JButton backBtn = new JButton("Back");
        backBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        backBtn.setPreferredSize(new Dimension(120, 40));
        backBtn.addActionListener(e -> {
            new HomePage().setVisible(true);
            dispose();
        });
        
        buttonPanel.add(loginBtn);
        buttonPanel.add(backBtn);
        
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        Student student = Database.authenticateStudent(username, password);
        
        if (student != null) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            new StudentDashboard(student).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password!", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

// StudentDashboard.java - Student dashboard with menu options

class StudentDashboard extends JFrame {
    private Student student;
    private JPanel contentPanel;
    
    public StudentDashboard(Student student) {
        this.student = student;
        
        setTitle("Student Dashboard - " + student.getName());
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        // Top panel with welcome message
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(60, 179, 113));
        JLabel welcomeLabel = new JLabel("Welcome, " + student.getName());
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.BLACK);
        topPanel.add(welcomeLabel);
        
        // Menu panel (left side)
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4, 1, 5, 5));
        menuPanel.setPreferredSize(new Dimension(200, 0));
        menuPanel.setBackground(new Color(70, 130, 180));
        
        JButton applyBtn = new JButton("Apply Internship");
        JButton viewBtn = new JButton("View Internship");
        JButton certBtn = new JButton("My Certificates");
        JButton logoutBtn = new JButton("Logout");
        
        styleMenuButton(applyBtn);
        styleMenuButton(viewBtn);
        styleMenuButton(certBtn);
        styleMenuButton(logoutBtn);
        
        applyBtn.addActionListener(e -> showApplyInternship());
        viewBtn.addActionListener(e -> showViewInternship());
        certBtn.addActionListener(e -> showMyCertificates());
        logoutBtn.addActionListener(e -> {
            new HomePage().setVisible(true);
            dispose();
        });
        
        menuPanel.add(applyBtn);
        menuPanel.add(viewBtn);
        menuPanel.add(certBtn);
        menuPanel.add(logoutBtn);
        
        // Content panel (center)
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        
        JLabel welcomeContent = new JLabel("Select an option from the menu", SwingConstants.CENTER);
        welcomeContent.setFont(new Font("Arial", Font.PLAIN, 18));
        contentPanel.add(welcomeContent, BorderLayout.CENTER);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(menuPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        
        // Show apply internship by default
        showApplyInternship();
    }
    
    private void styleMenuButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }
    
    private void showApplyInternship() {
        contentPanel.removeAll();
        contentPanel.add(new ApplyInternshipPanel(student));
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private void showViewInternship() {
        contentPanel.removeAll();
        contentPanel.add(new ViewInternshipPanel(student));
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private void showMyCertificates() {
        contentPanel.removeAll();
        contentPanel.add(new MyCertificatesPanel(student));
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}

// ApplyInternshipPanel.java - Panel for applying to internships

class ApplyInternshipPanel extends JPanel {
    private Student student;
    private JComboBox<Company> companyCombo;
    private JTextField nameField;
    private JTextField deptField;
    private JTextField fromDateField;
    private JTextField toDateField;
    private JTextField resumeField;
    private File selectedFile;
    
    public ApplyInternshipPanel(Student student) {
        this.student = student;
        
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Title
        JLabel titleLabel = new JLabel("Apply for Internship");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Company selection
        JLabel companyLabel = new JLabel("Select Company:");
        companyLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        companyCombo = new JComboBox<>();
        for (Company company : Database.companies) {
            companyCombo.addItem(company);
        }
        companyCombo.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Student name
        JLabel nameLabel = new JLabel("Student Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nameField = new JTextField(student.getName());
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        nameField.setEditable(false);
        
        // Department
        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        deptField = new JTextField(student.getDepartment());
        deptField.setFont(new Font("Arial", Font.PLAIN, 14));
        deptField.setEditable(false);
        
        // Duration from
        JLabel fromLabel = new JLabel("Duration From:");
        fromLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        fromDateField = new JTextField(15);
        fromDateField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Duration to
        JLabel toLabel = new JLabel("Duration To:");
        toLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        toDateField = new JTextField(15);
        toDateField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Resume upload
        JLabel resumeLabel = new JLabel("Resume/Offer Letter:");
        resumeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        resumeField = new JTextField(15);
        resumeField.setFont(new Font("Arial", Font.PLAIN, 14));
        resumeField.setEditable(false);
        
        JButton browseBtn = new JButton("Browse");
        browseBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        browseBtn.addActionListener(e -> browseFile());
        
        // Add components to form
        int row = 0;
        
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        formPanel.add(companyLabel, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        formPanel.add(companyCombo, gbc);
        row++;
        
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        formPanel.add(nameField, gbc);
        row++;
        
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        formPanel.add(deptLabel, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        formPanel.add(deptField, gbc);
        row++;
        
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        formPanel.add(fromLabel, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        formPanel.add(fromDateField, gbc);
        row++;
        
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        formPanel.add(toLabel, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        formPanel.add(toDateField, gbc);
        row++;
        
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        formPanel.add(resumeLabel, gbc);
        gbc.gridx = 1; gbc.gridwidth = 1;
        formPanel.add(resumeField, gbc);
        gbc.gridx = 2; gbc.gridwidth = 1;
        formPanel.add(browseBtn, gbc);
        
        // Submit button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        JButton submitBtn = new JButton("Submit Application");
        submitBtn.setFont(new Font("Arial", Font.BOLD, 16));
        submitBtn.setPreferredSize(new Dimension(200, 40));
        submitBtn.setBackground(new Color(60, 179, 113));
        submitBtn.setForeground(Color.BLACK);
        submitBtn.addActionListener(e -> submitApplication());
        buttonPanel.add(submitBtn);
        
        add(titleLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void browseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Resume/Offer Letter");
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            resumeField.setText(selectedFile.getName());
        }
    }
    
    private void submitApplication() {
        Company selectedCompany = (Company) companyCombo.getSelectedItem();
        String fromDate = fromDateField.getText().trim();
        String toDate = toDateField.getText().trim();
        
        if (selectedCompany == null || fromDate.isEmpty() || toDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String resumePath = selectedFile != null ? selectedFile.getAbsolutePath() : "Not uploaded";
        
        // Create and add application
        Application application = new Application(
            student.getUsername(),
            selectedCompany.getName(),
            student.getName(),
            student.getDepartment(),
            fromDate,
            toDate,
            resumePath
        );
        
        Database.applications.add(application);
        
        JOptionPane.showMessageDialog(this, 
            "Application submitted successfully!\nStatus: Pending", 
            "Success", JOptionPane.INFORMATION_MESSAGE);
        
        // Clear fields
        fromDateField.setText("");
        toDateField.setText("");
        resumeField.setText("");
        selectedFile = null;
    }
}

// ViewInternshipPanel.java - Panel to view internship applications

class ViewInternshipPanel extends JPanel {
    private Student student;
    private JTable table;
    private DefaultTableModel tableModel;
    
    public ViewInternshipPanel(Student student) {
        this.student = student;
        
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Title
        JLabel titleLabel = new JLabel("My Internship Applications");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Table
        String[] columns = {"Company", "From Date", "To Date", "Status", "Mentor"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(70, 130, 180));
        table.getTableHeader().setForeground(Color.BLACK);
        
        loadApplications();
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        
        // Refresh button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        refreshBtn.addActionListener(e -> loadApplications());
        buttonPanel.add(refreshBtn);
        
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void loadApplications() {
        tableModel.setRowCount(0);
        ArrayList<Application> apps = Database.getApplicationsByStudent(student.getUsername());
        
        for (Application app : apps) {
            Object[] row = {
                app.getCompanyName(),
                app.getFromDate(),
                app.getToDate(),
                app.getStatus(),
                app.getMentor()
            };
            tableModel.addRow(row);
        }
    }
}

// MyCertificatesPanel.java - Panel to view completed internships

class MyCertificatesPanel extends JPanel {
    private Student student;
    private JTable table;
    private DefaultTableModel tableModel;
    
    public MyCertificatesPanel(Student student) {
        this.student = student;
        
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Title
        JLabel titleLabel = new JLabel("My Certificates");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Table
        String[] columns = {"Company", "From Date", "To Date", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(60, 179, 113));
        table.getTableHeader().setForeground(Color.BLACK);
        
        loadCertificates();
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        
        JButton downloadBtn = new JButton("Download Certificate");
        downloadBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        downloadBtn.setBackground(new Color(60, 179, 113));
        downloadBtn.setForeground(Color.BLACK);
        downloadBtn.addActionListener(e -> downloadCertificate());
        
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        refreshBtn.addActionListener(e -> loadCertificates());
        
        buttonPanel.add(downloadBtn);
        buttonPanel.add(refreshBtn);
        
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void loadCertificates() {
        tableModel.setRowCount(0);
        ArrayList<Application> completed = Database.getCompletedInternships(student.getUsername());
        
        for (Application app : completed) {
            Object[] row = {
                app.getCompanyName(),
                app.getFromDate(),
                app.getToDate(),
                app.getStatus()
            };
            tableModel.addRow(row);
        }
        
        if (completed.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No completed internships yet!", 
                "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
 // Continue from MyCertificatesPanel.java
    private void downloadCertificate() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select a certificate to download!", 
                "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String company = (String) tableModel.getValueAt(selectedRow, 0);
        JOptionPane.showMessageDialog(this, 
            "Certificate for " + company + " downloaded successfully!\n(Simulated download)", 
            "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}

// ==================== ADMIN CLASSES ====================

// AdminLoginPage.java - Admin login page

class AdminLoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    
    // Fixed admin credentials
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    
    public AdminLoginPage() {
        setTitle("Admin Login");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 248, 255));
        
        // Title
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(220, 20, 60));
        JLabel titleLabel = new JLabel("Admin Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titlePanel.add(titleLabel);
        
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(usernameLabel, gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(usernameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(passwordLabel, gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(passwordField, gbc);
        
        // Info label
        JLabel infoLabel = new JLabel("(Default: admin / admin123)");
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        infoLabel.setForeground(Color.BLACK);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(infoLabel, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 248, 255));
        
        JButton loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 16));
        loginBtn.setPreferredSize(new Dimension(120, 40));
        loginBtn.setBackground(new Color(220, 20, 60));
        loginBtn.setForeground(Color.BLACK);
        loginBtn.addActionListener(e -> login());
        
        JButton backBtn = new JButton("Back");
        backBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        backBtn.setPreferredSize(new Dimension(120, 40));
        backBtn.addActionListener(e -> {
            new HomePage().setVisible(true);
            dispose();
        });
        
        buttonPanel.add(loginBtn);
        buttonPanel.add(backBtn);
        
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            JOptionPane.showMessageDialog(this, "Admin login successful!");
            new AdminDashboard().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid admin credentials!", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

// AdminDashboard.java - Admin dashboard

class AdminDashboard extends JFrame {
    private JPanel contentPanel;
    
    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(220, 20, 60));
        JLabel titleLabel = new JLabel("Admin Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLACK);
        topPanel.add(titleLabel);
        
        // Menu panel
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 1, 5, 5));
        menuPanel.setPreferredSize(new Dimension(220, 0));
        menuPanel.setBackground(new Color(70, 130, 180));
        
        JButton approveBtn = new JButton("Approve Applications");
        JButton assignBtn = new JButton("Assign Mentor");
        JButton logoutBtn = new JButton("Logout");
        
        styleMenuButton(approveBtn);
        styleMenuButton(assignBtn);
        styleMenuButton(logoutBtn);
        
        approveBtn.addActionListener(e -> showApproveApplications());
        assignBtn.addActionListener(e -> showAssignMentor());
        logoutBtn.addActionListener(e -> {
            new HomePage().setVisible(true);
            dispose();
        });
        
        menuPanel.add(approveBtn);
        menuPanel.add(assignBtn);
        menuPanel.add(logoutBtn);
        
        // Content panel
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        
        JLabel welcomeLabel = new JLabel("Select an option from the menu", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        contentPanel.add(welcomeLabel, BorderLayout.CENTER);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(menuPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        
        // Show approve applications by default
        showApproveApplications();
    }
    
    private void styleMenuButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }
    
    private void showApproveApplications() {
        contentPanel.removeAll();
        contentPanel.add(new ApproveApplicationsPanel());
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private void showAssignMentor() {
        contentPanel.removeAll();
        contentPanel.add(new AssignMentorPanel());
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}

// ApproveApplicationsPanel.java - Panel for approving applications

class ApproveApplicationsPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    
    public ApproveApplicationsPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Title
        JLabel titleLabel = new JLabel("Approve Internship Applications");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Table
        String[] columns = {"Student", "Company", "Department", "From", "To", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(220, 20, 60));
        table.getTableHeader().setForeground(Color.BLACK);
        
        loadApplications();
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        
        JButton approveBtn = new JButton("Approve Selected");
        approveBtn.setFont(new Font("Arial", Font.BOLD, 14));
        approveBtn.setBackground(new Color(60, 179, 113));
        approveBtn.setForeground(Color.BLACK);
        approveBtn.addActionListener(e -> approveApplication());
        
        JButton completeBtn = new JButton("Mark as Completed");
        completeBtn.setFont(new Font("Arial", Font.BOLD, 14));
        completeBtn.setBackground(new Color(70, 130, 180));
        completeBtn.setForeground(Color.BLACK);
        completeBtn.addActionListener(e -> markCompleted());
        
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        refreshBtn.addActionListener(e -> loadApplications());
        
        buttonPanel.add(approveBtn);
        buttonPanel.add(completeBtn);
        buttonPanel.add(refreshBtn);
        
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void loadApplications() {
        tableModel.setRowCount(0);
        
        for (Application app : Database.applications) {
            Object[] row = {
                app.getStudentName(),
                app.getCompanyName(),
                app.getDepartment(),
                app.getFromDate(),
                app.getToDate(),
                app.getStatus()
            };
            tableModel.addRow(row);
        }
    }
    
    private void approveApplication() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select an application to approve!", 
                "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Application app = Database.applications.get(selectedRow);
        
        if (app.getStatus().equals("Approved") || app.getStatus().equals("Completed")) {
            JOptionPane.showMessageDialog(this, 
                "This application is already " + app.getStatus() + "!", 
                "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        app.setStatus("Approved");
        loadApplications();
        
        JOptionPane.showMessageDialog(this, 
            "Application approved successfully!", 
            "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void markCompleted() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select an application!", 
                "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Application app = Database.applications.get(selectedRow);
        
        if (!app.getStatus().equals("Approved")) {
            JOptionPane.showMessageDialog(this, 
                "Only approved applications can be marked as completed!", 
                "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        app.setStatus("Completed");
        loadApplications();
        
        JOptionPane.showMessageDialog(this, 
            "Application marked as completed!", 
            "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}

// AssignMentorPanel.java - Panel for assigning mentors

class AssignMentorPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    
    public AssignMentorPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Title
        JLabel titleLabel = new JLabel("Assign Mentor to Applications");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Table
        String[] columns = {"Student", "Company", "Department", "Status", "Current Mentor"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(220, 20, 60));
        table.getTableHeader().setForeground(Color.BLACK);
        
        loadApplications();
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        
        JButton assignBtn = new JButton("Assign Mentor");
        assignBtn.setFont(new Font("Arial", Font.BOLD, 14));
        assignBtn.setBackground(new Color(220, 20, 60));
        assignBtn.setForeground(Color.BLACK);
        assignBtn.addActionListener(e -> assignMentor());
        
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        refreshBtn.addActionListener(e -> loadApplications());
        
        buttonPanel.add(assignBtn);
        buttonPanel.add(refreshBtn);
        
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void loadApplications() {
        tableModel.setRowCount(0);
        
        for (Application app : Database.applications) {
            Object[] row = {
                app.getStudentName(),
                app.getCompanyName(),
                app.getDepartment(),
                app.getStatus(),
                app.getMentor()
            };
            tableModel.addRow(row);
        }
    }
    
    private void assignMentor() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select an application!", 
                "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Application app = Database.applications.get(selectedRow);
        
        String mentorName = JOptionPane.showInputDialog(this, 
            "Enter Mentor Name:", 
            "Assign Mentor", 
            JOptionPane.PLAIN_MESSAGE);
        
        if (mentorName != null && !mentorName.trim().isEmpty()) {
            app.setMentor(mentorName.trim());
            loadApplications();
            
            JOptionPane.showMessageDialog(this, 
                "Mentor assigned successfully!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}   