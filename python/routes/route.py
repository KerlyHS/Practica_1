from flask import Blueprint, render_template, request, redirect, flash, url_for
import requests
import json

router = Blueprint('router', __name__)

# Lista de Inversionistas
@router.route('/inversionista/lista')
def listaInversionista(msg=''):
    # Hacer la solicitud para obtener la lista de inversionistas actualizada
    try:
        r = requests.get('http://localhost:8099/myapp/inversionista/list')
        r.raise_for_status()  # Lanza una excepción para errores de HTTP
        data = r.json()
        return render_template("servidor/servidor.html", lista=data["data"], message=msg)
    except requests.exceptions.RequestException as e:
        flash("Error al cargar la lista de inversionistas: " + str(e), category="error")
        return redirect(url_for('router.listaInversionista'))

# Registrar nuevo Inversionista
@router.route('/servidor/crearS/', methods=['GET', 'POST'])
def registrarInversionista():
    if request.method == 'POST':
        headers = {'Content-Type': 'application/json'}
        form = request.form
        dataF = {
            "nombre": form.get("nombre"),   # Usar .get() en lugar de acceso directo
            "dni": form.get("dni"),
            "apellido": form.get("apellido"),
            "montoI": form.get("montoI")
        }

        # Validaciones básicas
        if not dataF["nombre"] or not dataF["dni"] or not dataF["apellido"] or not dataF["montoI"]:
            flash('Todos los campos son obligatorios', category='error')
            return render_template('servidor/registroI.html')

        try:
            # Enviar datos al backend para registrar el inversionista
            r = requests.post('http://localhost:8099/myapp/inversionista/save', data=json.dumps(dataF), headers=headers)
            r.raise_for_status()  # Lanza error si la respuesta no es 200 OK
            dat = r.json()
            if r.status_code == 200:
                flash('Inversionista registrado con éxito', category='info')
                return redirect(url_for('router.listaInversionista'))  # Redirigir a la lista de inversionistas
            else:
                flash(dat.get('data', 'Error al registrar el inversionista'), category='error')
                return render_template('servidor/registroI.html')

        except requests.exceptions.RequestException as e:
            flash(f'Error al conectar con el servicio: {str(e)}', category='error')
            return render_template('servidor/registroI.html')

    return render_template('servidor/registroI.html')  # Si es GET, muestra el formulario

# Modificar Inversionista
@router.route('/router/modificarS/<id>', methods=['GET', 'POST'])
def editarInversionista(id):
    # Solicitar los datos actuales del inversionista
    try:
        r = requests.get(f'http://localhost:8099/myapp/inversionista/get/{id}')
        r.raise_for_status()
        data1 = r.json()
    except requests.exceptions.RequestException as e:
        flash(f'Error al obtener los datos del inversionista: {str(e)}', category='error')
        return redirect(url_for('router.listaInversionista'))

    if request.method == 'POST':
        form = request.form  # Obtener datos del formulario
        dataF = {
            "id": id,
            "nombre": form["nombre"],
            "apellido": form["apellido"],
            "montoI": form["montoI"],
            "dni": form["dni"]
        }

        try:
            # Realizar la solicitud PUT al backend para actualizar el inversionista
            r = requests.put(f'http://localhost:8099/myapp/inversionista/update/{id}', data=json.dumps(dataF), headers={'Content-Type': 'application/json'})
            r.raise_for_status()
            dat = r.json()

            if r.status_code == 200:
                flash('Inversionista actualizado con éxito', category='info')
                return redirect(url_for('router.listaInversionista'))  # Redirigir a la lista de inversionistas
            else:
                flash(dat.get('data', 'Error al actualizar el inversionista'), category='error')
                return render_template('servidor/editServidor.html', inversionista=data1["data"])

        except requests.exceptions.RequestException as e:
            flash(f'Error al conectar con el servicio: {str(e)}', category='error')
            return render_template('servidor/editServidor.html', inversionista=data1["data"])

    # Si el método es GET, se muestra el formulario con los datos actuales
    return render_template('servidor/editServidor.html', inversionista=data1["data"])


#---------------------------------------------

# # Lista de Proyectos
@router.route('/servidor/proyecto/lista')
def listaProyectos(msg=''):
    try:
        r = requests.get('http://localhost:8099/myapp/proyecto/list')
        r.raise_for_status()
        data = r.json()
        return render_template("servidor/listaProyectos.html", lista=data["data"], message=msg)
    except requests.exceptions.RequestException as e:
        flash(f"Error al cargar la lista de proyectos: {str(e)}", category="error")
        return redirect(url_for('router.listaProyectos'))
# Registrar Proyecto
@router.route('/proyecto/registrar', methods=['GET', 'POST'])
def registrarProyecto():
    if request.method == 'POST':
        headers = {'Content-Type': 'application/json'}
        form = request.form
        dataF = {
            "nombre": form.get("nombre"),
            "descripcion": form.get("descripcion"),
            "monto": form.get("monto"),
            "fecha_inicio": form.get("fecha_inicio"),
            "fecha_fin": form.get("fecha_fin")
        }

        # Validaciones básicas
        if not dataF["nombre"] or not dataF["descripcion"] or not dataF["monto"] or not dataF["fecha_inicio"] or not dataF["fecha_fin"]:
            flash('Todos los campos son obligatorios', category='error')
            return render_template('servidor/registroProyecto.html')

        try:
            # Enviar datos al backend para registrar el proyecto
            r = requests.post('http://localhost:8099/myapp/proyecto/save', data=json.dumps(dataF), headers=headers)
            r.raise_for_status()  # Lanza error si la respuesta no es 200 OK
            dat = r.json()
            if r.status_code == 200:
                flash('Proyecto registrado con éxito', category='info')
                return redirect(url_for('router.listaProyectos'))  # Redirigir a la lista de proyectos
            else:
                flash(dat.get('data', 'Error al registrar el proyecto'), category='error')
                return render_template('servidor/registroProyecto.html')

        except requests.exceptions.RequestException as e:
            flash(f'Error al conectar con el servicio: {str(e)}', category='error')
            return render_template('servidor/registroProyecto.html')

    return render_template('servidor/registroProyecto.html')  # Si es GET, muestra el formulario
