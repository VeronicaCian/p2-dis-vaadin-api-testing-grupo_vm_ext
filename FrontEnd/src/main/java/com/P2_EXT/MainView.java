package com.P2_EXT;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.router.Route;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The main view contains a button and a click listener.
 */
@Route
@PWA(name = "My Application", shortName = "My Application")
public class MainView extends VerticalLayout {


    //declaramos las variables final para el proyecto
    final VerticalLayout layout;
    final Tab pestañaUser;
    final Tab pestañaPrestamo;
    final Tab pestañaEquipo;
    final Map<Tab, Component> tabsToPages = new HashMap<>();
    final Tabs tabs;
    final TextField filtros;


    public MainView() {

        this.filtros = new TextField();

        //INICIO LAYOUT USUARIOS

        VerticalLayout VerticalUsersLayout = new VerticalLayout();
        HorizontalLayout HorizontalUsersLayout = new HorizontalLayout();

        //hacemos referencia a la pestaña de usuarios --> tab
        pestañaUser = new Tab(VaadinIcon.USER.create(),new Span("Usuarios"));
        Div divUsers = new Div();



        //nos creamos el boton de añadir un nuevo usuario
        Button btnNewUser = new Button("Nuevo User");
        Button btnAgregarUser = new Button("Añadir");
        Button buscar = new Button("Buscar usuario");

        //Inicio grid Usuarios

        Grid<Usuarios> UsersGrid = new Grid<>(Usuarios.class);
        UsersGrid.setItems(users);
        UsersGrid.removeColumnByKey("id");
        //UsersGrid.setColumns("nombre", "departamento", "ubicacion", "telefono", "email");
        UsersGrid.addColumn(Usuarios::getNombre).setHeader("Nombre");
        UsersGrid.addColumn(Usuarios::getDepartamento).setHeader("Departamento");
        UsersGrid.addColumn(Usuarios::getTelefono).setHeader("Ubicacion");
        UsersGrid.addColumn(Usuarios::getEmail).setHeader("telefono");
        UsersGrid.addColumn(Usuarios::getUbicacion).setHeader("email");

        UsersGrid.setWidth("100%");

        //listener para cuando el usuario selecciones una fila del grid
        UsersGrid.asSingleSelect().addValueChangeListener(e -> {
            modalinfo(e.getValue());
        });


        //al pulsar el boton de nuevo usuario
        /*btnNewUser.addClickListener( e ->{
            Dialog dialog = new Dialog();
            VerticalLayout dialogLayout = createDialogUserLayout(dialog);
            dialog.add(dialogLayout);

            Button cancelButton = new Button("Cancelar", e1 -> dialog.close());
            dialog.add(cancelButton);
            //add(dialog,cancelButton);


            dialog.open();

            // Center the button within the example
            getStyle().set("position", "fixed").set("top", "0").set("right", "0")
                    .set("bottom", "0").set("left", "0").set("display", "flex")
                    .set("align-items", "center").set("justify-content", "center");
        });*/

        //formulario para un nuevo usuario
        btnNewUser.addClickListener(e-> nuevoModalUser());



        //fin grid Usuarios

        HorizontalUsersLayout.add(btnNewUser,buscar);
        VerticalUsersLayout.add(filtros,UsersGrid, HorizontalUsersLayout);
        divUsers.add(VerticalUsersLayout);
        divUsers.getStyle().set("flex-wrap", "wrap");


        //filtros para buscar
        filtros.setValueChangeMode(ValueChangeMode.EAGER);
        filtros.addValueChangeListener(e ->{

            // modalinfo(e.getValue());
           /*String user= Getuser(e.getValue());
           Usuarios userfiltro = gson2.fromJson(usuariosarray, listausers);
           if(userfiltro != null){
               UsersGrid.setItems(userfiltro);
           }else{
               UsersGrid.setItems(users);
           }*/

        });



        //FIN LAYOUT USUARIOS




        //INCIO TABSHEET

        tabsToPages.put(pestañaUser,divUsers);
        tabsToPages.put(pestañaEquipo,divEquipos);
        tabsToPages.put(pestañaPrestamo,divPrestamos);

        tabs = new Tabs(pestañaUser, pestañaEquipo,pestañaPrestamo);
        tabs.setSizeFull();
        Div paginas = new Div(divUsers, divEquipos,divPrestamos);
        paginas.setSizeFull();


        tabs.addSelectedChangeListener(event ->{
            //tabsToPages.values().forEach(page -> page.setVisible(false));
            //Component paginaSeleccionada = tabsToPages.get(tabs.getSelectedTab());
            //paginaSeleccionada.setVisible(true);
            setLayout(event.getSelectedTab());
        });

        layout = new VerticalLayout();
        layout.setSpacing(false);
        setLayout(tabs.getSelectedTab());

        add(tabs, paginas,layout);

        //FIN TABSHEET

    }


    //FIN CONTSTRUCTOR DE MAINVIEW



    private VerticalLayout createDialogUserLayout(Dialog dialog) {

        H2 headline = new H2("Nuevo usuario");
        headline.getStyle().set("margin", "0").set("font-size", "1.5em")
                .set("font-weight", "bold");

        HorizontalLayout header = new HorizontalLayout(headline);
        header.getElement().getClassList().add("draggable");
        header.setSpacing(false);

        //Definicion de los textfield del formulario
        AtomicInteger id_user = new AtomicInteger();
        TextField Nombre = new TextField("Nombre");
        TextField Departamento = new TextField("Departamento");
        IntegerField telefono = new IntegerField("Telefono");
        TextField email = new TextField("Email");
        TextField Ubicacion = new TextField("Ubicación");

        VerticalLayout dialogLayout = new VerticalLayout(Nombre,Departamento,Ubicacion, telefono, email);
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

        //botones del formulario
        //utton btnAgregarUser = new Button("Añadir", e-> dialog.close());
        Button btnAñadir = new Button("Añadir" ,e -> {
            //agregamos la funcioalidad de agregar el usuarios con los datos del textfield
            //instanciamos un nuevo usuario
            int id = id_user.intValue();

            Usuarios user = new Usuarios(id,Nombre.getValue(),Departamento.getValue(),Ubicacion.getValue(),telefono.getValue(),email.getValue());
            //MainView m = new MainView();
            crearUser(user);
            UI.getCurrent().getPage().reload();



        });

        HorizontalLayout buttonLayout = new HorizontalLayout(btnAñadir);
        VerticalLayout dialogLayout2 = new VerticalLayout(header, dialogLayout,buttonLayout);
        dialog.add(btnAñadir);

        return dialogLayout;
    }






    //Inicio de los metodos

    private void setLayout(Tab tab) {
        layout.removeAll();

        if (tab.equals(pestañaUser)) {
            tabsToPages.values().forEach(page -> page.setVisible(false));
            Component paginaSeleccionada = tabsToPages.get(tabs.getSelectedTab());
            paginaSeleccionada.setVisible(true);
        } else if (tab.equals(pestañaEquipo)) {
            tabsToPages.values().forEach(page -> page.setVisible(false));
            Component paginaSeleccionada = tabsToPages.get(tabs.getSelectedTab());
            paginaSeleccionada.setVisible(true);
        } else if (tab.equals(pestañaPrestamo)){
            tabsToPages.values().forEach(page -> page.setVisible(false));
            Component paginaSeleccionada = tabsToPages.get(tabs.getSelectedTab());
            paginaSeleccionada.setVisible(true);
        }
    }

    //Funcion modalinfo para mostrar la informacion del usuario
    private void modalinfo(Usuarios user){
        try {
            Dialog dialog = new Dialog(); //nos instanciamos un nuevo dialogo
            dialog.setCloseOnEsc(false);
            dialog.setCloseOnOutsideClick(false);

            //ponemos en el layout los campos a mostrar
            dialog.add(new HorizontalLayout(new Html("<b>Nombre:  </b>"),new Text(user.getNombre())));
            dialog.add(new HorizontalLayout(new Html("<b>Departamento:  </b>"), new Text(user.getDepartamento())));
            dialog.add(new HorizontalLayout(new Html("<b>Ubicacion:  </b>"),new Text(user.getUbicacion())));
            dialog.add(new HorizontalLayout(new Html("<b>Telefono:  </b>"), new Text(String.valueOf(user.getTelefono()))));
            dialog.add(new HorizontalLayout(new Html("<b>email:  </b>"),new Text(user.getEmail())));

            //imprim por consola el id del usuario para ver si lo coge bien y poder eliminarlo
            System.out.println(user.getId());

            Button modificaruser = new Button("Editar", event -> {dialog.close(); editarmodaluser(user);});
            Button deleteuser = new Button("Eliminar", event -> {
                deleteUser(user.getId());
                UI.getCurrent().getPage().reload();

            });
            Button cancelButton = new Button("Cancelar", event -> { dialog.close(); });
            HorizontalLayout actions2 = new HorizontalLayout(modificaruser, cancelButton,deleteuser);
            dialog.add(actions2);
            dialog.open();

            //estilo para el boton de eliminar --> rojo
            deleteuser.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_ERROR);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
