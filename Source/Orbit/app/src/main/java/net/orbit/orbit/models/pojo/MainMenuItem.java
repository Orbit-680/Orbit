package net.orbit.orbit.models.pojo;

public class MainMenuItem
{
    private int label;
    private int title;
    private int image;



    private String role;
    public MainMenuItem()
    {

    }

    public MainMenuItem(int label, int title, int image, String role)
    {
        this.image = image;
        this.label = label;
        this.title = title;
        this.role = role;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public void setTitle(int  title) { this.title = title; }

    public void setImage(int image) {
        this.image = image;
    }

    public void setRole(String role){this.role = role;}

    public int getLabel() {
        return label;
    }

    public int getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public String getRole() { return role;}

}
