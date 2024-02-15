# -*- coding: utf-8 -*-
{
    'name': "library_member",

    'summary': """
        Short (1 phrase/line) summary of the module's purpose, used as
        subtitle on modules listing or apps.openerp.com""",

    'description': """
        Gestionar los prestamos de los miembros de la biblioteca
    """,

    'author': "My Company",
    'website': "http://www.yourcompany.com",

    # Categories can be used to filter modules in modules listing
    # Check https://github.com/odoo/odoo/blob/15.0/odoo/addons/base/data/ir_module_category_data.xml
    # for the full list
    'category': 'Uncategorized',
    'version': '0.1',

    # any module necessary for this one to work correctly
    'depends': ['library_app', "mail"],

    # always loaded
    'data': [
        "security/library_security.xml",
        "security/ir.model.acces.csv",
        "views/book_view.xml",
        "views/library_menu.xml",
        "views/member_view.xml",
        "views/book_list_template.xml",
    ],
    
    'application': False,
    'license': 'AGPL-3',
    'installable': True
}
