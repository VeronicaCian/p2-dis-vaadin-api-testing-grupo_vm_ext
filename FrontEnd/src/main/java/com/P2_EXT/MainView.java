package com.P2_EXT;

import com.P2_EXT.Clases.Prestamos;
import com.P2_EXT.Clases.Usuarios;
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





        //INICIO LAYOUT PRESTAMOS

        VerticalLayout VerticalPrestamoLayout = new VerticalLayout();
        HorizontalLayout HorizontalPrestamoLayout = new HorizontalLayout();

        //hacemos referencia a la pestaña de usuarios --> tab
        pestañaPrestamo = new Tab(VaadinIcon.DOLLAR.create(),new Span("Prestamos"));
        Div divPrestamos = new Div();

        //nos creamos el boton de añadir un nuevo prestamo
        Button btnNewPrestamo = new Button("Nuevo Prestamo");


        TextField usuario = new TextField("Usuario_Id");
        TextField equipo = new TextField("Equipo id");
        TextField fechaIni = new TextField("Fecha Inicio");
        TextField fechaFin = new TextField("Fecha fin ");
        TextField comentarios = new TextField("Comentarios");


        //Incicio de grid Prestamos

        Grid<Prestamos> PrestamoGrid = new Grid<>(Prestamos.class);
        PrestamoGrid.setItems(prestamos);
        PrestamoGrid.removeColumnByKey("id");
        PrestamoGrid.setColumns("usuario_Id","fecha_Inicio_Prestamo","fecha_Fin_Prestamo","comentarios");
        PrestamoGrid.addColumn(Prestamos::getUsuario_Id);
        PrestamoGrid.addColumn(Prestamos::getFecha_Inicio_Prestamo);
        PrestamoGrid.addColumn(Prestamos::getFecha_Fin_Prestamo);
        PrestamoGrid.addColumn(Prestamos::getComentarios);

        //listener para cuando el usuario selecciones una fila del grid
        PrestamoGrid.asSingleSelect().addValueChangeListener(e -> {
            modalinfoPrestamo(e.getValue());
        });


        //al pulsar el boton de nuevo usuario
        btnNewPrestamo.addClickListener( e ->nuevoModalPrestamo());





        //Fin del grid Prestamos

        VerticalPrestamoLayout.add(PrestamoGrid,btnNewPrestamo);
        divPrestamos.add(VerticalPrestamoLayout);
        VerticalUsersLayout.getStyle().set("flex-wrap", "wrap");


        //FIN LAYOUT PRESTAMOS





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

    //MOdal para modificar un usuario
    private void editarmodaluser(Usuarios user){

        Dialog dialog = new Dialog(); //nos creamos un nuevo dialog para el formulario de mdificar el usuario
        dialog.setCloseOnOutsideClick(false);
        dialog.setCloseOnEsc(false);

        //Declaramos los campos que van a aparecer para editar el modal
        //lo campos son los declrados anteriormente
        //usamos TextField

        TextField Nombre = new TextField("Nombre");
        Nombre.setValue(user.getNombre());
        dialog.add(new HorizontalLayout(Nombre));
        TextField Departamento = new TextField("Departamento");
        Departamento.setValue(user.getDepartamento());
        dialog.add(new HorizontalLayout(Departamento));
        TextField Ubicacion = new TextField("Ubicacion");
        Ubicacion.setValue(user.getUbicacion());
        dialog.add(new HorizontalLayout(Ubicacion));
        TextField telefono = new TextField("Telefono");
        telefono.setValue(String.valueOf(user.getTelefono()));
        dialog.add(new HorizontalLayout(telefono));
        TextField email = new TextField("email");
        email.setValue(user.getEmail());
        dialog.add(new HorizontalLayout(email));


        //creamos el boton de aceptar para modificar el usuario

        Button aceptar = new Button("Confirmar", e -> {

            user.setNombre(Nombre.getValue());
            user.setDepartamento(Departamento.getValue());
            user.setUbicacion(Ubicacion.getValue());
            user.setTelefono(Integer.parseInt(telefono.getValue()));
            user.setEmail(email.getValue());

            //guardamos los cambios
            //LLAMADA A FUNCION DE PUT como modificaUser(user)
            modificarUser(user);
            UI.getCurrent().getPage().reload();
            dialog.close();

        });

        //cremoa un boton de abortar o cancelar y los añadirmos al layout
        Button cancelar = new Button("Cancelar", event -> { dialog.close(); });
        HorizontalLayout opt = new HorizontalLayout(aceptar, cancelar);
        dialog.add(opt);
        //abrimos el modal
        dialog.open();

    }

    //nuev modal para crear un usuario
    void nuevoModalUser(){
        Dialog dialog = new Dialog(); //instaciamos un nuevo dialogo

        //nos declaramos los campos del formulario necesarios para crear un nuevo usuario

        AtomicInteger idusuario = new AtomicInteger();
        TextField Nombre = new TextField("Nombre");
        dialog.add(new HorizontalLayout(Nombre));
        TextField Departamento = new TextField("Departamento");
        dialog.add(new HorizontalLayout(Departamento));
        TextField Ubicacion = new TextField("Ubicacion");
        dialog.add(new HorizontalLayout(Ubicacion));
        IntegerField telefono = new IntegerField("telefono");
        dialog.add(new HorizontalLayout(telefono));
        TextField email = new TextField("email");
        dialog.add(new HorizontalLayout(email));

        //creamos el boton de aceptar
        Button aceptar = new Button("Añadir", event -> {

            int id = idusuario.intValue();

            Usuarios user = new Usuarios(id,Nombre.getValue(),Departamento.getValue(),Ubicacion.getValue(),telefono.getValue(),email.getValue());
            //crearUser(user);

            user.setId(idusuario.getAndIncrement());
            user.setNombre(Nombre.getValue());
            user.setDepartamento(Departamento.getValue());
            user.setUbicacion(Ubicacion.getValue());
            //user.setTelefono(Integer.parseInt(telefono.getValue()));
            user.setTelefono(telefono.getValue());
            user.setEmail(email.getValue());

            crearUser(user);
            UI.getCurrent().getPage().reload();
            dialog.close();
        });

        Button cancelar = new Button("Cancelar", event2 ->{
            dialog.close();
        });

        HorizontalLayout opciones = new HorizontalLayout(aceptar,cancelar);
        dialog.add(opciones);
        dialog.open();


    }


    //METODOS PARA LA PESTAÑA DE PRESTAMOS

    //modal iinformacion cuando el usuario quiere ver info de un prestamos
    private void modalinfoPrestamo(Prestamos prestamo){

        try{
            //nos generemos un nuevo dialogo
            Dialog dialogo = new Dialog();
            dialogo.setCloseOnEsc(false);
            dialogo.setCloseOnOutsideClick(false);

            //ponemos en el layout los textfields que vamos a usar
            dialogo.add(new HorizontalLayout(new Html("<b>id Usuario: </b>"), new Text(String.valueOf(prestamo.getUsuario_Id()))));
            dialogo.add(new HorizontalLayout(new Html("<b>id Equipo: </b>"), new Text(String.valueOf(prestamo.getEquipo_Id()))));
            dialogo.add(new HorizontalLayout(new Html("<b>Fecha Inicio Prestamo: </b>"),new Text(prestamo.getFecha_Inicio_Prestamo())));
            dialogo.add(new HorizontalLayout(new Html("<b>Fecha Fin prestamos: </b>"),new Text(prestamo.getFecha_Fin_Prestamo())));
            dialogo.add(new HorizontalLayout(new Html("<b>Fecha Real dev: </b>"),new Text(prestamo.getFecha_Real_Dev())));
            dialogo.add(new HorizontalLayout(new Html("<b>Comentarios: </b>"),new Text(prestamo.getComentarios())));

            Button modificarprestamo = new Button("Editar", event -> {dialogo.close(); editarmodalPrestamo(prestamo);});
            Button deleteprestamo = new Button("Eliminar");
            Button cancelButton = new Button("Cancelar", event -> { dialogo.close(); });
            HorizontalLayout actions2 = new HorizontalLayout(modificarprestamo, cancelButton,deleteprestamo);
            dialogo.add(actions2);
            dialogo.open();

            //estilo para el boton de eliminar --> rojo
            deleteprestamo.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_ERROR);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}
