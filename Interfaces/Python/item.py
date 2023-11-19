#
#       URL y partes de la URL
#
# Protocolo HTTP:   https://es.wikipedia.org/wiki/Protocolo_de_transferencia_de_hipertexto
# Path parameters:  https://fastapi.tiangolo.com/tutorial/path-params/
# Query parameters: https://fastapi.tiangolo.com/tutorial/query-params/
# Resquest body:    https://fastapi.tiangolo.com/tutorial/body/
# Anotated types:   https://stackoverflow.com/questions/71898644/how-to-use-python-typing-annotated
#                   https://fastapi.tiangolo.com/python-types/#type-hints-with-metadata-annotations
#                   https://fastapi.tiangolo.com/tutorial/query-params-str-validations/#advantages-of-annotated
# pydantic:         https://docs.pydantic.dev/2.0/
# Browser y cookies https://clearcode.cc/blog/browsers-first-third-party-cookies/
#                   https://caniuse.com/?search=cookie
# Tutorial completo https://www.tutorialspoint.com/fastapi/fastapi_cookie_parameters.htm#:~:text=To%20read%20back%20the%20cookie,object%20in%20the%20FastAPI%20library.&text=Inspect%20these%20two%20endpoints%20in,bound%20to%20%22%2Fcookies%22.
#
#
# OTROS PUNTOS QUE VER
#       Como ver la documentacion del api creada
#       Como hacer validaciones  de tipos de datos
#       Como hacer inyección de dependencias
#       Como hacer autenticaciones en base a sesiones

from os import name
from fastapi import FastAPI     # Permite crear un backend de una aplicación web
from fastapi import Cookie      # Necesario para recibir cookies
from fastapi.responses import HTMLResponse  # Necesario para enviar html
from fastapi.responses import JSONResponse  # Necesario para las cookies
from pydantic import BaseModel  # Para crear/validar tipos de datos complejos
from typing import Union        # Permite crear tipos de datos con varias opciones
from enum import Enum

import uvicorn           # Permite crear enumerables


#
# Clases de datos (DTO) pydantic y enumerables
#
class Item(BaseModel):
    name: str
    description: Union[str, None] = None
    price: float
    tax: Union[float, None] = None


class Book(BaseModel):
    title: str
    author:  str


class MealTypes(str, Enum):
    meat = "*"
    vegy = "**"
    past = "***"


app = FastAPI()


#
# Creación de rutas y de funciones de atención a las rutas
#
@app.get("/")
async def root():
    return({"Probando lo básico de fastAPI"})


# -- Como devolver una página web
@app.get("/html", response_class=HTMLResponse)
async def read_items():
    html_content = """
    <html>
        <head>
            <title>Some HTML in here</title>
        </head>
        <body>
            <h1>Probando lo básico de fastAPI y HTML</h1>
        </body>
    </html>
    """
    return HTMLResponse(content=html_content, status_code=200)

# REQUEST BODY:
#       Los datos viajan como payload en formato JSON
#       Se reciben y conviertten com 'model_dump()'

@app.post("/dto_pydantic/")
async def create_item(item: Item):
    item_dict = item.model_dump()
    if item.tax:
        price_with_tax = item.price + item.tax
        item_dict.update({"price_with_tax": price_with_tax})
    return item_dict


# ---- PATH PARAMETERS: Parámetros que se envian en la propia ruta
@app.get("/path_parameters/simple/{mi_path_parameter}")
async def get_path_parameters(mi_path_parameter: str):
    # Recibir 'path parameters' simple
    return {"Mi path parameter es: " + mi_path_parameter}


@app.get("/path_parameters/complex/{my_favorit}")
async def get_path_parameters(my_favorit: MealTypes):
    # Recibir un 'path parameter' complejo en este caso un Enum
    # Permite validar los paramétros que se aceptan...
    return {"My option is... " + my_favorit.name + " · (" + my_favorit.value+")"}

# QUERY PARAMETERS:
#      Parejas de clave-valor despues del ? en una ULR
#      Si hay varias parejas se separan entre si con &.
#      Ejemplo: http://127.0.0.1:8000/query_parameters/?title=Caperucita&author=El lobo


@app.get("/query_parameters/book")
async def read_item(title: str = None, author: str = None):
    return "Erase una vez un " + author + " que se comió a " + title


# COOKIES:
# Una cookie es una pareja clave/valor que se guarda persistentemente en el cliente

# -- Crear una cookie
@app.get("/cookie/set/")
async def create_cookie():
    response = JSONResponse(
        content="Ahora tu ordenador tiene una cookie más ...")
    response.set_cookie(key="dato_secreto", value="me gusta la cocacola!")
    return response

# -- Recuperar una cookie


@app.get("/cookie/get/")
async def read_cookie(dato_secreto: str = Cookie(None)):
    return {"Tu dato secreto es ": dato_secreto}


@app.get("/cookie/set/expires/")
async def create_cookie():
    response = JSONResponse(
        content="Ahora tu ordenador tiene una cookie que expiera en 1 minuto ...")
    response.set_cookie(
        key='dato_que_caduca',
        value='como un suspiro...',
        max_age=30,
        expires=30
    )
    return response


@app.get("/cookie/set/expires/get")
async def read_cookie_caduca(dato_que_caduca: str = Cookie(None)):
    return {"Tu dato que caduca es ": dato_que_caduca}


@app.get("/cookie/delete/")
async def create_cookie():
    response = JSONResponse(content="He eliminado la cookie ...")
    response.delete_cookie(key="dato_secreto")
    return response
