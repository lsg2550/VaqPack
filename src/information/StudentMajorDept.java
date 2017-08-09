package information;

//Student's Major Dprt info - location, phone #, list of profressors, and their info, and link to website
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;

public class StudentMajorDept {

    public void smD(ChoiceBox<String> cb, Hyperlink hl) {
        switch (cb.getValue()) {
            case "Accounting":
                hl.setText("http://www.utrgv.edu/cobe/departments/accountancy/index.htm");
                break;
            case "Anthropology":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/liberal-arts/sociology-and-anthropology/index.htm");
                break;
            case "Applied Arts and Sciences":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/liberal-arts/contact-us/index.htm");
                break;
            case "Art":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/fine-arts/index.htm");
                break;
            case "Biology":
                hl.setText("http://www.utrgv.edu/cos/");
                break;
            case "Biomedical Sciences":
                hl.setText("http://www.utrgv.edu/hbs/index.htm");
                break;
            case "Chemistry":
                hl.setText("http://www.utrgv.edu/cos/");
                break;
            case "Civil Engineering":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/engineering-computer-science/index.htm");
                break;
            case "Clinical Laboratory Sciences":
                hl.setText("http://www.utrgv.edu/hbs/index.htm");
                break;
            case "Communication Sciences and Disorders":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/liberal-arts/");
                break;
            case "Communication Studies":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/liberal-arts/");
                break;
            case "Computational Science":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/engineering-computer-science/index.htm");
                break;
            case "Computer Engineering":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/engineering-computer-science/index.htm");
                break;
            case "Computer Information Systems Technology":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/engineering-computer-science/index.htm");
                break;
            case "Computer Science":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/engineering-computer-science/index.htm");
                break;
            case "Criminal Justice":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/liberal-arts/");
                break;
            case "Criminology and Criminal Justice":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/liberal-arts/");
                break;
            case "Dance":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/fine-arts/index.htm");
                break;
            case "Dietetics":
                hl.setText("http://www.utrgv.edu/hbs/index.htm");
                break;
            case "Early Care and Early Childhood":
                hl.setText("http://www.utrgv.edu/cep/index.htm");
                break;
            case "Economics":
                hl.setText("http://www.utrgv.edu/cobe/departments/economics-finance/index.htm");
                break;
            case "Electrical Engineering":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/engineering-computer-science/index.htm");
                break;
            case "Engineering Physics":
                hl.setText("http://www.utrgv.edu/cos/");
                break;
            case "Engineering Technology":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/engineering-computer-science/index.htm");
                break;
            case "English":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/liberal-arts/");
                break;
            case "Environmental Sciences":
                hl.setText("http://www.utrgv.edu/cos/");
                break;
            case "Exercise Science":
                hl.setText("http://www.utrgv.edu/hhp/index.htm");
                break;
            case "Finance":
                hl.setText("http://www.utrgv.edu/cobe/departments/economics-finance/index.htm");
                break;
            case "Health":
                hl.setText("http://www.utrgv.edu/hhp/index.htm");
                break;
            case "Health Services Technology":
                hl.setText("http://www.utrgv.edu/hbs/index.htm");
                break;
            case "History":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/liberal-arts/");
                break;
            case "Information Systems":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/engineering-computer-science/index.htm");
                break;
            case "Interdisciplinary Studies":
                hl.setText("http://www.utrgv.edu/cep/index.htm");
                break;
            case "International Business":
                hl.setText("http://www.utrgv.edu/cobe/");
                break;
            case "Kinesiology":
                hl.setText("http://www.utrgv.edu/hhp/index.htm");
                break;
            case "Management":
                hl.setText("http://www.utrgv.edu/businessphd/concentrations/management/index.htm");
                break;
            case "Manufacturing Engineering":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/engineering-computer-science/index.htm");
                break;
            case "Marine Biology":
                hl.setText("http://www.utrgv.edu/cos/departments/seems/index.htm");
                break;
            case "Marketing":
                hl.setText("http://www.utrgv.edu/en-us/about-utrgv/administration/marketing-communications/");
                break;
            case "Mass Communication":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/liberal-arts/");
                break;
            case "Materials Management and Logistics":
                hl.setText("http://www.utrgv.edu/cobe/departments/international/potential-careers/bs_materials_logistics/index.htm");
                break;
            case "Mathematics":
                hl.setText("http://www.utrgv.edu/math/");
                break;
            case "Mechanical Engineering":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/engineering-computer-science/index.htm");
                break;
            case "Mexican American Studies":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/liberal-arts/");
                break;
            case "Multidisciplinary Studies":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/liberal-arts/");
                break;
            case "Music":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/fine-arts/index.htm");
                break;
            case "Nursing":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/health-affairs/");
                break;
            case "Performance":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/fine-arts/");
                break;
            case "Philosophy":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/liberal-arts/");
                break;
            case "Physical Science":
                hl.setText("http://www.utrgv.edu/cos/");
                break;
            case "Physics":
                hl.setText("http://www.utrgv.edu/cos/");
                break;
            case "Political Science":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/liberal-arts/");
                break;
            case "Psychology":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/liberal-arts/");
                break;
            case "Rehabiliation Services - Deaf Studies":
                hl.setText("http://www.utrgv.edu/rehab-counseling/undergraduate-programs/bachelor-of-science-in-rehabilitation-services-deaf-studies/index.htm");
                break;
            case "Rehabiliation Services":
                hl.setText("http://www.utrgv.edu/rehab-counseling/");
                break;
            case "Social Studies Composite":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/liberal-arts/");
                break;
            case "Social Work":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/health-affairs/");
                break;
            case "Sociology":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/liberal-arts/");
                break;
            case "Spanish":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/liberal-arts/");
                break;
            case "Spanish Translation and Interpreting":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/liberal-arts/");
                break;
            case "Theater":
                hl.setText("http://www.utrgv.edu/en-us/academics/colleges/fine-arts/index.htm");
                break;
            default:
                hl.setText("http://www.utrgv.edu/en-us/");
                break;
        }
    }

    //Display List of Majors
    public void majorList(ChoiceBox<String> cb) {
        /*
        String[] a = {
            "Accounting", "Anthropology", "Applied Arts and Sciences", "Art", "Biology", "Biomedical Sciences", "Chemistry", "Civil Engineering", "Clinical Laboratory Sciences", "Communication Sciences and Disorders", "Communication Studies", "Computational Science", "Computer Engineering", "Computer Information Systems Technology", "Computer Science", "Criminal Justice", "Criminology and Criminal Justice", "Dance", "Dietetics", "Early Care and Early Childhood", "Economics", "Electrical Engineering", "Engineering Physics",
            "Engineering Technology", "English", "Environmental Sciences",
            "Exercise Science", "Finance", "Health", "Health Services Technology", "History", "Information Systems", "Interdisciplinary Studies", "International Business", "Kinesiology", "Management", "Manufacturing Engineering", "Management", "Marketing", "Mass Communication", "Materials Management and Logistics", "Mathematics", "Mechnical Engineering", "Mexican American Studies",
            "Multidisciplinary Studies", "Music", "Nursing", "Performance", "Philosophy", "Physical Science", "Physics", "Political Science", "Psychology",
            "Rehabiliation Services - Deaf Studies", "Rehabiliation Services",
            "Social Studies Composite", "Social Work", "Sociology", "Spanish",
            "Spanish Translation and Interpreting", "Theater"
        };*/
        cb.getItems().addAll("Accounting", "Anthropology", "Applied Arts and Sciences", "Art", "Biology", "Biomedical Sciences", "Chemistry", "Civil Engineering", "Clinical Laboratory Sciences", "Communication Sciences and Disorders", "Communication Studies", "Computational Science", "Computer Engineering", "Computer Information Systems Technology", "Computer Science", "Criminal Justice", "Criminology and Criminal Justice", "Dance", "Dietetics", "Early Care and Early Childhood", "Economics", "Electrical Engineering", "Engineering Physics",
                "Engineering Technology", "English", "Environmental Sciences",
                "Exercise Science", "Finance", "Health", "Health Services Technology", "History", "Information Systems", "Interdisciplinary Studies", "International Business", "Kinesiology", "Management", "Manufacturing Engineering", "Management", "Marketing", "Mass Communication", "Materials Management and Logistics", "Mathematics", "Mechnical Engineering", "Mexican American Studies",
                "Multidisciplinary Studies", "Music", "Nursing", "Performance", "Philosophy", "Physical Science", "Physics", "Political Science", "Psychology",
                "Rehabiliation Services - Deaf Studies", "Rehabiliation Services",
                "Social Studies Composite", "Social Work", "Sociology", "Spanish",
                "Spanish Translation and Interpreting", "Theater");
    }
}
