import java.util.ArrayList;
import java.util.List;

import interfaces.Reportable;
import models.Issue;
import models.Bug;
import models.Task;

public class IssueTrackerApplication {
    public static void main(String[] args){
        
        List<Issue> issues = new ArrayList<>();

        // Addition of Bug instances
        issues.add(new Bug(1, "NullPointer Exception", "Occurs on login", "High"));
        issues.add(new Bug(2, "UI Misalignment", "Button not centered", "Low"));

        // Addition of Task instances
        issues.add(new Task(3, "Implement Feature X", "Add new login method", "Alice", 5));
        issues.add(new Task(4, "Update Documentation", "Revise API docs", "Bob", 2));

        // Displaying issues 
        System.out.println("=== Issue List ===");
        for (Issue issue : issues) {
            issue.display();
        }

        // Generated reports using Reportable interface
        System.out.println("\n=== Reports ===");
        for (Issue issue : issues) {
            if (issue instanceof Reportable) {
                Reportable reportable = (Reportable) issue;
                reportable.generateReport();
            }
        }
    }
}
