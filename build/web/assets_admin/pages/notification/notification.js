// 'use strict';
//$(window).on('load',function(){
//    //Welcome Message (not for login page)
//    function notify(message, type){
//        $.growl({
//            message: message
//        },{
//            type: type,
//            allow_dismiss: false,
//            label: 'Cancel',
//            className: 'btn-xs btn-inverse',
//            placement: {
//                from: 'bottom',
//                align: 'right'
//            },
//            delay: 2500,
//            animate: {
//                    enter: 'animated fadeInRight',
//                    exit: 'animated fadeOutRight'
//            },
//            offset: {
//                x: 30,
//                y: 30
//            }
//        });
//    };
//   
//});


//Example: notifications("Chao mung den voi chung toi", "top", 'center', '', 'primary', '');
/* Trong ?ó:
 * 0 -> message
 * 1 -> v? trí xu?t hi?n (top, bottom)
 * 2 -> xu?t hi?n ? ?âu (center, left, right)
 * 3 -> icon
 * 4 -> ki?u thông báo (primary, inverse, info, success, warning, danger)
 * 5, 6 -> các ho?t ?nh..
 */


function notifications(message, from, align, icon, type, animIn, animOut) {
    $.growl({
        icon: icon,
        message: ` ${message} `
    }, {
        element: 'body',
        type: type,
        allow_dismiss: true,
        placement: {
            from: from,
            align: align
        },
        offset: {
            x: 30,
            y: 30
        },
        spacing: 10,
        z_index: 999999,
        delay: 2500,
        timer: 1000,
        url_target: '_blank',
        mouse_over: false,
        animate: {
            enter: animIn,
            exit: animOut
        },
        icon_type: 'class',
        template: '<div data-growl="container" class="alert" role="alert">' +
                '<button type="button" class="close" data-growl="dismiss">' +
                '<span aria-hidden="true">&times;</span>' +
                '<span class="sr-only">Close</span>' +
                '</button>' +
                '<span data-growl="icon"></span>' +
                '<span data-growl="title"></span>' +
                '<span data-growl="message"></span>' +
                '<a href="#" data-growl="url"></a>' +
                '</div>'
    });
}
$(document).ready(function () {

    function notify(from, align, icon, type, animIn, animOut) {
        $.growl({
            icon: icon,
            title: ' Bootstrap Growl ',
            message: 'Turning standard Bootstrap alerts into awesome notifications',
            url: ''
        }, {
            element: 'body',
            type: type,
            allow_dismiss: true,
            placement: {
                from: from,
                align: align
            },
            offset: {
                x: 30,
                y: 30
            },
            spacing: 10,
            z_index: 999999,
            delay: 2500,
            timer: 1000,
            url_target: '_blank',
            mouse_over: false,
            animate: {
                enter: animIn,
                exit: animOut
            },
            icon_type: 'class',
            template: '<div data-growl="container" class="alert" role="alert">' +
                    '<button type="button" class="close" data-growl="dismiss">' +
                    '<span aria-hidden="true">&times;</span>' +
                    '<span class="sr-only">Close</span>' +
                    '</button>' +
                    '<span data-growl="icon"></span>' +
                    '<span data-growl="title"></span>' +
                    '<span data-growl="message"></span>' +
                    '<a href="#" data-growl="url"></a>' +
                    '</div>'
        });
    }
    ;

    $('.notifications .btn').on('click', function (e) {
        e.preventDefault();
        var nFrom = $(this).attr('data-from');
        var nAlign = $(this).attr('data-align');
        var nIcons = $(this).attr('data-icon');
        var nType = $(this).attr('data-type');
        var nAnimIn = $(this).attr('data-animation-in');
        var nAnimOut = $(this).attr('data-animation-out');

        notify(nFrom, nAlign, nIcons, nType, nAnimIn, nAnimOut);
    });

});

