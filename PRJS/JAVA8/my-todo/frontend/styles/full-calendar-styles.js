/*
   Copyright 2019, Stefan Uebe

   Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
   documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
   rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
   permit persons to whom the Software is furnished to do so, subject to the following conditions:

   The above copyright notice and this permission notice shall be included in all copies or substantial portions
   of the Software.

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
   WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
   COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
   OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
/*
This file contains a list of generated custom properties that might be used to style the FullCalendar.
Simply remove the comment, change the value and refresh the page.

Please be aware that this list may custom properties that are nonsense to change. That is due to the generic
nature of the list.

The names of the properties are created by the css class selectors (including pseudo-selectors) and the
css properties for which they set a value.

Examples:

.fc button .fc-icon {
    top: ...;
}

is targeted by --fc_button_fc-icon-top.


.fc .fc-row .fc-content-skeleton td {
    background: ...;
}

is targeted by custom property --fc_fc-row_fc-content-skeleton_td-background


.fc .fc-toolbar > * > :first-child {
    margin-left: ...;
}

is targeted by --fc_fc-toolbar__LACE_BRACE___ASTERISK___LACE_BRACE___COLON_first-child-margin-left


All the css properties have also a default value, so you simply can clean the list and keep the custom properties
that you need.

*/

const $_documentContainer = document.createElement('template');
$_documentContainer.innerHTML = `
<custom-style>
    <style>
        html{
            /*--a_SQUARE_BRACKET_OPEN_data-goto_SQUARE_BRACKET_CLOSE_-cursor: pointer;*/
            /*--a_SQUARE_BRACKET_OPEN_data-goto_SQUARE_BRACKET_CLOSE__COLON_hover-text-decoration: underline;*/
            /*--afc-more-cursor: pointer;*/
            /*--afc-more-font-size: 0.85em;*/
            /*--afc-more-margin: 1px 3px;*/
            /*--afc-more-text-decoration: none;*/
            /*--afc-more_COLON_hover-text-decoration: underline;*/
            /*--body_fc-font-size: 1em;*/
            /*--fc-bg-bottom: 0;*/
            /*--fc-bg-left: 0;*/
            /*--fc-bg-position: absolute;*/
            /*--fc-bg-right: 0;*/
            /*--fc-bg-top: 0;*/
            /*--fc-bg_table-height: 100%;*/
            /*--fc-bgevent-background: rgb(143, 223, 130);*/
            /*--fc-bgevent-opacity: 0.3;*/
            /*--fc-bgevent-skeleton-left: 0;*/
            /*--fc-bgevent-skeleton-position: absolute;*/
            /*--fc-bgevent-skeleton-right: 0;*/
            /*--fc-bgevent-skeleton-top: 0;*/
            /*--fc-button--moz-user-select: none;*/
            /*--fc-button--ms-user-select: none;*/
            /*--fc-button--webkit-appearance: button;*/
            /*--fc-button--webkit-user-select: none;*/
            /*--fc-button-background-color: transparent;*/
            /*--fc-button-border: 1px solid transparent;*/
            /*--fc-button-border-radius: 0;*/
            /*--fc-button-color: rgb(33, 37, 41);*/
            /*--fc-button-display: inline-block;*/
            /*--fc-button-font-family: inherit;*/
            /*--fc-button-font-size: inherit;*/
            /*--fc-button-font-weight: 400;*/
            /*--fc-button-group-display: inline-flex;*/
            /*--fc-button-group-display--ms-inline-flexbox: -ms-inline-flexbox;*/
            /*--fc-button-group-display--webkit-inline-box: -webkit-inline-box;*/
            /*--fc-button-group-position: relative;*/
            /*--fc-button-group-vertical-align: middle;*/
            /*--fc-button-group__LACE_BRACE__fc-button--ms-flex: 1 1 auto;*/
            /*--fc-button-group__LACE_BRACE__fc-button--webkit-box-flex: 1;*/
            /*--fc-button-group__LACE_BRACE__fc-button-flex: 1 1 auto;*/
            /*--fc-button-group__LACE_BRACE__fc-button-position: relative;*/
            /*--fc-button-group__LACE_BRACE__fc-button_COLON_active-z-index: 1;*/
            /*--fc-button-group__LACE_BRACE__fc-button_COLON_focus-z-index: 1;*/
            /*--fc-button-group__LACE_BRACE__fc-button_COLON_hover-z-index: 1;*/
            /*--fc-button-group__LACE_BRACE__fc-button_COLON_not_R_BRACKET_OPEN__COLON_first-child_R_BRACKET_CLOSE_-border-bottom-left-radius: 0;*/
            /*--fc-button-group__LACE_BRACE__fc-button_COLON_not_R_BRACKET_OPEN__COLON_first-child_R_BRACKET_CLOSE_-border-top-left-radius: 0;*/
            /*--fc-button-group__LACE_BRACE__fc-button_COLON_not_R_BRACKET_OPEN__COLON_first-child_R_BRACKET_CLOSE_-margin-left: -1px;*/
            /*--fc-button-group__LACE_BRACE__fc-button_COLON_not_R_BRACKET_OPEN__COLON_last-child_R_BRACKET_CLOSE_-border-bottom-right-radius: 0;*/
            /*--fc-button-group__LACE_BRACE__fc-button_COLON_not_R_BRACKET_OPEN__COLON_last-child_R_BRACKET_CLOSE_-border-top-right-radius: 0;*/
            /*--fc-button-group__LACE_BRACE__fc-buttonfc-button-active-z-index: 1;*/
            /*--fc-button-line-height: inherit;*/
            /*--fc-button-margin: 0;*/
            /*--fc-button-overflow: visible;*/
            /*--fc-button-padding: 0.4em 0.65em;*/
            /*--fc-button-primary-background-color: rgb(44, 62, 80);*/
            /*--fc-button-primary-border-color: rgb(44, 62, 80);*/
            /*--fc-button-primary-color: rgb(255, 255, 255);*/
            /*--fc-button-primary_COLON_disabled-background-color: rgb(44, 62, 80);*/
            /*--fc-button-primary_COLON_disabled-border-color: rgb(44, 62, 80);*/
            /*--fc-button-primary_COLON_disabled-color: rgb(255, 255, 255);*/
            /*--fc-button-primary_COLON_focus--webkit-box-shadow: 0 0 0 0.2rem rgba(76, 91, 106, 0.5);*/
            /*--fc-button-primary_COLON_focus-box-shadow: 0 0 0 0.2rem rgba(76, 91, 106, 0.5);*/
            /*--fc-button-primary_COLON_hover-background-color: rgb(30, 43, 55);*/
            /*--fc-button-primary_COLON_hover-border-color: rgb(26, 37, 47);*/
            /*--fc-button-primary_COLON_hover-color: rgb(255, 255, 255);*/
            /*--fc-button-primary_COLON_not_R_BRACKET_OPEN__COLON_disabled_R_BRACKET_CLOSE__COLON_active-background-color: rgb(26, 37, 47);*/
            /*--fc-button-primary_COLON_not_R_BRACKET_OPEN__COLON_disabled_R_BRACKET_CLOSE__COLON_active-border-color: rgb(21, 30, 39);*/
            /*--fc-button-primary_COLON_not_R_BRACKET_OPEN__COLON_disabled_R_BRACKET_CLOSE__COLON_active-color: rgb(255, 255, 255);*/
            /*--fc-button-primary_COLON_not_R_BRACKET_OPEN__COLON_disabled_R_BRACKET_CLOSE__COLON_active_COLON_focus--webkit-box-shadow: 0 0 0 0.2rem rgba(76, 91, 106, 0.5);*/
            /*--fc-button-primary_COLON_not_R_BRACKET_OPEN__COLON_disabled_R_BRACKET_CLOSE__COLON_active_COLON_focus-box-shadow: 0 0 0 0.2rem rgba(76, 91, 106, 0.5);*/
            /*--fc-button-primary_COLON_not_R_BRACKET_OPEN__COLON_disabled_R_BRACKET_CLOSE_fc-button-active-background-color: rgb(26, 37, 47);*/
            /*--fc-button-primary_COLON_not_R_BRACKET_OPEN__COLON_disabled_R_BRACKET_CLOSE_fc-button-active-border-color: rgb(21, 30, 39);*/
            /*--fc-button-primary_COLON_not_R_BRACKET_OPEN__COLON_disabled_R_BRACKET_CLOSE_fc-button-active-color: rgb(255, 255, 255);*/
            /*--fc-button-primary_COLON_not_R_BRACKET_OPEN__COLON_disabled_R_BRACKET_CLOSE_fc-button-active_COLON_focus--webkit-box-shadow: 0 0 0 0.2rem rgba(76, 91, 106, 0.5);*/
            /*--fc-button-primary_COLON_not_R_BRACKET_OPEN__COLON_disabled_R_BRACKET_CLOSE_fc-button-active_COLON_focus-box-shadow: 0 0 0 0.2rem rgba(76, 91, 106, 0.5);*/
            /*--fc-button-text-align: center;*/
            /*--fc-button-text-transform: none;*/
            /*--fc-button-user-select: none;*/
            /*--fc-button-vertical-align: middle;*/
            /*--fc-button_COLON__COLON_-moz-focus-inner-border-style: none;*/
            /*--fc-button_COLON__COLON_-moz-focus-inner-padding: 0;*/
            /*--fc-button_COLON_disabled-opacity: 0.65;*/
            /*--fc-button_COLON_focus--webkit-box-shadow: 0 0 0 0.2rem rgba(44, 62, 80, 0.25);*/
            /*--fc-button_COLON_focus-box-shadow: 0 0 0 0.2rem rgba(44, 62, 80, 0.25);*/
            /*--fc-button_COLON_focus-outline: 1px dotted;*/
            /*--fc-button_COLON_hover-color: rgb(33, 37, 41);*/
            /*--fc-button_COLON_hover-text-decoration: none;*/
            /*--fc-button_COLON_not_R_BRACKET_OPEN__COLON_disabled_R_BRACKET_CLOSE_-cursor: pointer;*/
            /*--fc-button_fc-icon-font-size: 1.5em;*/
            /*--fc-button_fc-icon-vertical-align: middle;*/
            /*--fc-day-grid-event-margin: 1px 2px 0;*/
            /*--fc-day-grid-event-padding: 0 1px;*/
            /*--fc-day-grid-event_fc-content-overflow: hidden;*/
            /*--fc-day-grid-event_fc-content-white-space: nowrap;*/
            /*--fc-day-grid-event_fc-time-font-weight: bold;*/
            /*--fc-day-grid_fc-row-z-index: 1;*/
            /*--fc-day-topfc-other-month-opacity: 0.3;*/
            /*--fc-dayGrid-view_fc-body_fc-row-min-height: 4em;*/
            /*--fc-dayGrid-view_fc-day-number-padding: 2px;*/

            /*--fc-dayGrid-view_fc-day-top_fc-week-number-background-color: rgb(242, 242, 242);*/
            /*--fc-dayGrid-view_fc-day-top_fc-week-number-color: rgb(128, 128, 128);*/


            /*--fc-dayGrid-view_fc-day-top_fc-week-number-min-width: 1.5em;*/
            /*--fc-dayGrid-view_fc-day-top_fc-week-number-text-align: center;*/
            /*--fc-dayGrid-view_fc-week-number-padding: 2px;*/
            /*--fc-dayGrid-view_tdfc-week-number-text-align: center;*/
            /*--fc-dayGrid-view_tdfc-week-number__LACE_BRACE___ASTERISK_-display: inline-block;*/
            /*--fc-dayGrid-view_tdfc-week-number__LACE_BRACE___ASTERISK_-min-width: 1.25em;*/
            /*--fc-dayGrid-view_thfc-day-number-padding: 0 2px;*/
            /*--fc-dayGrid-view_thfc-week-number-padding: 0 2px;*/
            /*--fc-dayGridDay-view_fc-content-skeleton-padding-bottom: 1em;*/
            /*--fc-dayGridWeek-view_fc-content-skeleton-padding-bottom: 1em;*/
            /*--fc-direction: ltr;*/
            /*--fc-divider-border-style: solid;*/
            /*--fc-divider-border-width: 1px;*/

            /*--fc-event-background-color: rgb(55, 136, 216);*/
            /*--fc-event-border: 1px solid rgb(55, 136, 216);*/
            /*--fc-event-border-radius: 3px;*/

            --fc-event-color: rgb(0,0,0);

            /*--fc-event-display: block;*/
            /*--fc-event-dot-background-color: rgb(55, 136, 216);*/
            /*--fc-event-dot-border-radius: 5px;*/
            /*--fc-event-dot-display: inline-block;*/
            /*--fc-event-dot-height: 10px;*/
            /*--fc-event-dot-width: 10px;*/
            /*--fc-event-font-size: 0.85em;*/
            /*--fc-event-line-height: 1.4;*/
            /*--fc-event-position: relative;*/
            /*--fc-event-text-decoration: none;*/
            /*--fc-event_COLON_hover-color: rgb(255, 255, 255);*/
            /*--fc-event_COLON_hover-text-decoration: none;*/
            /*--fc-event_SQUARE_BRACKET_OPEN_href_SQUARE_BRACKET_CLOSE_-cursor: pointer;*/
            /*--fc-event_fc-content-position: relative;*/
            /*--fc-event_fc-content-z-index: 2;*/
            /*--fc-event_fc-resizer-display: none;*/
            /*--fc-event_fc-resizer-position: absolute;*/
            /*--fc-event_fc-resizer-z-index: 4;*/
            /*--fc-eventfc-allow-mouse-resize_fc-resizer-display: block;*/
            /*--fc-eventfc-draggable-cursor: pointer;*/
            /*--fc-eventfc-dragging_COLON_not_R_BRACKET_OPEN_fc-selected_R_BRACKET_CLOSE_-opacity: 0.75;*/
            /*--fc-eventfc-draggingfc-selected-box-shadow: 0 2px 7px rgba(0, 0, 0, 0.3);*/
            /*--fc-eventfc-selected-box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);*/
            /*--fc-eventfc-selected-z-index: 9999;*/
            /*--fc-eventfc-selected_COLON_after-background: rgb(0, 0, 0);*/
            /*--fc-eventfc-selected_COLON_after-bottom: -1px;*/
            /*--fc-eventfc-selected_COLON_after-content: "";*/
            /*--fc-eventfc-selected_COLON_after-left: -1px;*/
            /*--fc-eventfc-selected_COLON_after-opacity: 0.25;*/
            /*--fc-eventfc-selected_COLON_after-position: absolute;*/
            /*--fc-eventfc-selected_COLON_after-right: -1px;*/
            /*--fc-eventfc-selected_COLON_after-top: -1px;*/
            /*--fc-eventfc-selected_COLON_after-z-index: 1;*/
            /*--fc-eventfc-selected_fc-resizer-display: block;*/
            /*--fc-eventfc-selected_fc-resizer_COLON_before-content: "";*/
            /*--fc-eventfc-selected_fc-resizer_COLON_before-height: 40px;*/
            /*--fc-eventfc-selected_fc-resizer_COLON_before-left: 50%;*/
            /*--fc-eventfc-selected_fc-resizer_COLON_before-margin-left: -20px;*/
            /*--fc-eventfc-selected_fc-resizer_COLON_before-margin-top: -20px;*/
            /*--fc-eventfc-selected_fc-resizer_COLON_before-position: absolute;*/
            /*--fc-eventfc-selected_fc-resizer_COLON_before-top: 50%;*/
            /*--fc-eventfc-selected_fc-resizer_COLON_before-width: 40px;*/
            /*--fc-eventfc-selected_fc-resizer_COLON_before-z-index: 9999;*/
            /*--fc-h-eventfc-allow-mouse-resize_fc-resizer-bottom: -1px;*/
            /*--fc-h-eventfc-allow-mouse-resize_fc-resizer-top: -1px;*/
            /*--fc-h-eventfc-allow-mouse-resize_fc-resizer-width: 7px;*/
            /*--fc-h-eventfc-selected_COLON_before-bottom: -10px;*/
            /*--fc-h-eventfc-selected_COLON_before-content: "";*/
            /*--fc-h-eventfc-selected_COLON_before-left: 0;*/
            /*--fc-h-eventfc-selected_COLON_before-position: absolute;*/
            /*--fc-h-eventfc-selected_COLON_before-right: 0;*/
            /*--fc-h-eventfc-selected_COLON_before-top: -10px;*/
            /*--fc-h-eventfc-selected_COLON_before-z-index: 3;*/
            /*--fc-h-eventfc-selected_fc-resizer-background: rgb(255, 255, 255);*/
            /*--fc-h-eventfc-selected_fc-resizer-border-color: inherit;*/
            /*--fc-h-eventfc-selected_fc-resizer-border-radius: 4px;*/
            /*--fc-h-eventfc-selected_fc-resizer-border-style: solid;*/
            /*--fc-h-eventfc-selected_fc-resizer-border-width: 1px;*/
            /*--fc-h-eventfc-selected_fc-resizer-height: 6px;*/
            /*--fc-h-eventfc-selected_fc-resizer-margin-top: -4px;*/
            /*--fc-h-eventfc-selected_fc-resizer-top: 50%;*/
            /*--fc-h-eventfc-selected_fc-resizer-width: 6px;*/
            /*--fc-highlight-background: rgb(188, 232, 241);*/
            /*--fc-highlight-opacity: 0.3;*/
            /*--fc-highlight-skeleton-left: 0;*/
            /*--fc-highlight-skeleton-position: absolute;*/
            /*--fc-highlight-skeleton-right: 0;*/
            /*--fc-highlight-skeleton-top: 0;*/
            /*--fc-icon--moz-osx-font-smoothing: grayscale;*/
            /*--fc-icon--webkit-font-smoothing: antialiased;*/
            /*--fc-icon-chevron-left_COLON_before-content: "";*/
            /*--fc-icon-chevron-right_COLON_before-content: "";*/
            /*--fc-icon-chevrons-left_COLON_before-content: "";*/
            /*--fc-icon-chevrons-right_COLON_before-content: "";*/
            /*--fc-icon-display: inline-block;*/
            /*--fc-icon-font-family: "fcicons";*/
            /*--fc-icon-font-style: normal;*/
            /*--fc-icon-font-variant: normal;*/
            /*--fc-icon-font-weight: normal;*/
            /*--fc-icon-height: 1em;*/
            /*--fc-icon-line-height: 1;*/
            /*--fc-icon-minus-square_COLON_before-content: "";*/
            /*--fc-icon-plus-square_COLON_before-content: "";*/
            /*--fc-icon-speak: none;*/
            /*--fc-icon-text-align: center;*/
            /*--fc-icon-text-transform: none;*/
            /*--fc-icon-width: 1em;*/
            /*--fc-icon-x_COLON_before-content: "";*/
            /*--fc-limited-display: none;*/
            /*--fc-list-empty-display: table-cell;*/
            /*--fc-list-empty-text-align: center;*/
            /*--fc-list-empty-vertical-align: middle;*/
            /*--fc-list-empty-wrap1-display: table;*/
            /*--fc-list-empty-wrap1-height: 100%;*/
            /*--fc-list-empty-wrap1-width: 100%;*/
            /*--fc-list-empty-wrap2-bottom: 0;*/
            /*--fc-list-empty-wrap2-left: 0;*/
            /*--fc-list-empty-wrap2-position: absolute;*/
            /*--fc-list-empty-wrap2-right: 0;*/
            /*--fc-list-empty-wrap2-top: 0;*/
            /*--fc-list-heading-border-bottom-width: 1px;*/
            /*--fc-list-heading_td-font-weight: bold;*/
            /*--fc-list-item-marker-white-space: nowrap;*/
            /*--fc-list-item-marker-width: 1px;*/
            /*--fc-list-item-time-white-space: nowrap;*/
            /*--fc-list-item-time-width: 1px;*/
            /*--fc-list-item-title_a-color: inherit;*/
            /*--fc-list-item-title_a-text-decoration: none;*/
            /*--fc-list-item-title_a_SQUARE_BRACKET_OPEN_href_SQUARE_BRACKET_CLOSE__COLON_hover-text-decoration: underline;*/
            /*--fc-list-itemfc-has-url-cursor: pointer;*/
            /*--fc-list-table_td-border-width: 1px 0 0;*/
            /*--fc-list-table_td-padding: 8px 14px;*/
            /*--fc-list-table_tr_COLON_first-child_td-border-top-width: 0;*/
            /*--fc-list-view-border-style: solid;*/
            /*--fc-list-view-border-width: 1px;*/
            /*--fc-ltr_fc-axis-text-align: right;*/
            /*--fc-ltr_fc-day-grid-eventfc-allow-mouse-resize_fc-end-resizer-margin-right: -2px;*/
            /*--fc-ltr_fc-day-grid-eventfc-allow-mouse-resize_fc-start-resizer-margin-left: -2px;*/
            /*--fc-ltr_fc-dayGrid-view_fc-day-top_fc-day-number-float: right;*/
            /*--fc-ltr_fc-dayGrid-view_fc-day-top_fc-week-number-border-radius: 0 0 3px 0;*/
            /*--fc-ltr_fc-dayGrid-view_fc-day-top_fc-week-number-float: left;*/
            /*--fc-ltr_fc-h-event_fc-end-resizer-cursor: e-resize;*/
            /*--fc-ltr_fc-h-event_fc-end-resizer-right: -1px;*/
            /*--fc-ltr_fc-h-event_fc-start-resizer-cursor: w-resize;*/
            /*--fc-ltr_fc-h-event_fc-start-resizer-left: -1px;*/
            /*--fc-ltr_fc-h-eventfc-not-end-border-bottom-right-radius: 0;*/
            /*--fc-ltr_fc-h-eventfc-not-end-border-right-width: 0;*/
            /*--fc-ltr_fc-h-eventfc-not-end-border-top-right-radius: 0;*/
            /*--fc-ltr_fc-h-eventfc-not-end-margin-right: 0;*/
            /*--fc-ltr_fc-h-eventfc-not-end-padding-right: 1px;*/
            /*--fc-ltr_fc-h-eventfc-not-start-border-bottom-left-radius: 0;*/
            /*--fc-ltr_fc-h-eventfc-not-start-border-left-width: 0;*/
            /*--fc-ltr_fc-h-eventfc-not-start-border-top-left-radius: 0;*/
            /*--fc-ltr_fc-h-eventfc-not-start-margin-left: 0;*/
            /*--fc-ltr_fc-h-eventfc-not-start-padding-left: 1px;*/
            /*--fc-ltr_fc-h-eventfc-selected_fc-end-resizer-margin-right: -4px;*/
            /*--fc-ltr_fc-h-eventfc-selected_fc-start-resizer-margin-left: -4px;*/
            /*--fc-ltr_fc-list-heading-alt-float: right;*/
            /*--fc-ltr_fc-list-heading-main-float: left;*/
            /*--fc-ltr_fc-list-item-marker-padding-right: 0;*/
            /*--fc-ltr_fc-time-grid_fc-event-container-margin: 0 2.5% 0 2px;*/
            /*--fc-ltr_fc-time-grid_fc-now-indicator-arrow-border-bottom-color: transparent;*/
            /*--fc-ltr_fc-time-grid_fc-now-indicator-arrow-border-top-color: transparent;*/
            /*--fc-ltr_fc-time-grid_fc-now-indicator-arrow-border-width: 5px 0 5px 6px;*/
            /*--fc-ltr_fc-time-grid_fc-now-indicator-arrow-left: 0;*/
            /*--fc-mirror-skeleton-left: 0;*/
            /*--fc-mirror-skeleton-position: absolute;*/
            /*--fc-mirror-skeleton-right: 0;*/
            /*--fc-mirror-skeleton-top: 0;*/
            /*--fc-mirror-skeleton_tr_COLON_first-child__LACE_BRACE__td__LACE_BRACE__fc-day-grid-event-margin-top: 0;*/
            /*--fc-more-popover-width: 220px;*/
            /*--fc-more-popover-z-index: 2;*/
            /*--fc-more-popover_fc-event-container-padding: 10px;*/
            --fc-nonbusiness-background: rgb(0, 0, 0);
            /*--fc-not-allowed-cursor: not-allowed;*/
            /*--fc-not-allowed_fc-event-cursor: not-allowed;*/
            /*--fc-now-indicator-border: 3 solid red;*/
            /*--fc-now-indicator-position: absolute;*/
            /*--fc-popover-box-shadow: 0 2px 6px rgba(0, 0, 0, 0.15);*/
            /*--fc-popover-position: absolute;*/
            /*--fc-popover_fc-header-align-items: center;*/
            /*--fc-popover_fc-header-display: flex;*/
            /*--fc-popover_fc-header-flex-direction: row;*/
            /*--fc-popover_fc-header-justify-content: space-between;*/
            /*--fc-popover_fc-header-padding: 2px 4px;*/
            /*--fc-popover_fc-header_fc-close-cursor: pointer;*/
            /*--fc-popover_fc-header_fc-close-font-size: 1.1em;*/
            /*--fc-popover_fc-header_fc-close-opacity: 0.65;*/
            /*--fc-popover_fc-header_fc-title-margin: 0 2px;*/
            /*--fc-row-position: relative;*/
            /*--fc-row_COLON_first-child_table-border-top: 0 hidden transparent;*/
            /*--fc-row_fc-bg-z-index: 1;*/
            /*--fc-row_fc-bgevent-skeleton-bottom: 0;*/
            /*--fc-row_fc-bgevent-skeleton-z-index: 2;*/
            /*--fc-row_fc-bgevent-skeleton_table-height: 100%;*/
            /*--fc-row_fc-bgevent-skeleton_td-border-color: transparent;*/
            /*--fc-row_fc-content-skeleton-padding-bottom: 2px;*/
            /*--fc-row_fc-content-skeleton-position: relative;*/
            /*--fc-row_fc-content-skeleton-z-index: 4;*/
            /*--fc-row_fc-content-skeleton_tbody_td-border-top: 0;*/
            /*--fc-row_fc-content-skeleton_td-border-bottom: 0;*/
            /*--fc-row_fc-highlight-skeleton-bottom: 0;*/
            /*--fc-row_fc-highlight-skeleton-z-index: 3;*/
            /*--fc-row_fc-highlight-skeleton_table-height: 100%;*/
            /*--fc-row_fc-highlight-skeleton_td-border-color: transparent;*/
            /*--fc-row_fc-mirror-skeleton-z-index: 5;*/
            /*--fc-row_fc-mirror-skeleton_tbody_td-border-top: 0;*/
            /*--fc-row_fc-mirror-skeleton_td-border-bottom: 0;*/
            /*--fc-row_table-border-bottom: 0 hidden transparent;*/
            /*--fc-row_table-border-left: 0 hidden transparent;*/
            /*--fc-row_table-border-right: 0 hidden transparent;*/
            /*--fc-rowfc-rigid-overflow: hidden;*/
            /*--fc-rowfc-rigid_fc-content-skeleton-left: 0;*/
            /*--fc-rowfc-rigid_fc-content-skeleton-position: absolute;*/
            /*--fc-rowfc-rigid_fc-content-skeleton-right: 0;*/
            /*--fc-rowfc-rigid_fc-content-skeleton-top: 0;*/
            /*--fc-rtl-text-align: right;*/
            /*--fc-rtl_fc-axis-text-align: left;*/
            /*--fc-rtl_fc-day-grid-eventfc-allow-mouse-resize_fc-end-resizer-margin-left: -2px;*/
            /*--fc-rtl_fc-day-grid-eventfc-allow-mouse-resize_fc-start-resizer-margin-right: -2px;*/
            /*--fc-rtl_fc-dayGrid-view_fc-day-top_fc-day-number-float: left;*/
            /*--fc-rtl_fc-dayGrid-view_fc-day-top_fc-week-number-border-radius: 0 0 0 3px;*/
            /*--fc-rtl_fc-dayGrid-view_fc-day-top_fc-week-number-float: right;*/
            /*--fc-rtl_fc-h-event_fc-end-resizer-cursor: w-resize;*/
            /*--fc-rtl_fc-h-event_fc-end-resizer-left: -1px;*/
            /*--fc-rtl_fc-h-event_fc-start-resizer-cursor: e-resize;*/
            /*--fc-rtl_fc-h-event_fc-start-resizer-right: -1px;*/
            /*--fc-rtl_fc-h-eventfc-not-end-border-bottom-left-radius: 0;*/
            /*--fc-rtl_fc-h-eventfc-not-end-border-left-width: 0;*/
            /*--fc-rtl_fc-h-eventfc-not-end-border-top-left-radius: 0;*/
            /*--fc-rtl_fc-h-eventfc-not-end-margin-left: 0;*/
            /*--fc-rtl_fc-h-eventfc-not-end-padding-left: 1px;*/
            /*--fc-rtl_fc-h-eventfc-not-start-border-bottom-right-radius: 0;*/
            /*--fc-rtl_fc-h-eventfc-not-start-border-right-width: 0;*/
            /*--fc-rtl_fc-h-eventfc-not-start-border-top-right-radius: 0;*/
            /*--fc-rtl_fc-h-eventfc-not-start-margin-right: 0;*/
            /*--fc-rtl_fc-h-eventfc-not-start-padding-right: 1px;*/
            /*--fc-rtl_fc-h-eventfc-selected_fc-end-resizer-margin-left: -4px;*/
            /*--fc-rtl_fc-h-eventfc-selected_fc-start-resizer-margin-right: -4px;*/
            /*--fc-rtl_fc-list-heading-alt-float: left;*/
            /*--fc-rtl_fc-list-heading-main-float: right;*/
            /*--fc-rtl_fc-list-item-marker-padding-left: 0;*/
            /*--fc-rtl_fc-list-view-direction: rtl;*/
            /*--fc-rtl_fc-popover_fc-header-flex-direction: row-reverse;*/
            /*--fc-rtl_fc-time-grid_fc-event-container-margin: 0 2px 0 2.5%;*/
            /*--fc-rtl_fc-time-grid_fc-now-indicator-arrow-border-bottom-color: transparent;*/
            /*--fc-rtl_fc-time-grid_fc-now-indicator-arrow-border-top-color: transparent;*/
            /*--fc-rtl_fc-time-grid_fc-now-indicator-arrow-border-width: 5px 6px 5px 0;*/
            /*--fc-rtl_fc-time-grid_fc-now-indicator-arrow-right: 0;*/
            /*--fc-scroller--webkit-overflow-scrolling: touch;*/
            /*--fc-scroller__LACE_BRACE__fc-day-grid-position: relative;*/
            /*--fc-scroller__LACE_BRACE__fc-day-grid-width: 100%;*/
            /*--fc-scroller__LACE_BRACE__fc-time-grid-position: relative;*/
            /*--fc-scroller__LACE_BRACE__fc-time-grid-width: 100%;*/
            /*--fc-text-align: left;*/
            /*--fc-time-grid-container-position: relative;*/
            /*--fc-time-grid-container-z-index: 1;*/
            /*--fc-time-grid-event-inset--webkit-box-shadow: 0px 0px 0px 1px rgb(255, 255, 255);*/
            /*--fc-time-grid-event-inset-box-shadow: 0px 0px 0px 1px rgb(255, 255, 255);*/
            /*--fc-time-grid-event-margin-bottom: 1px;*/
            /*--fc-time-grid-event_fc-content-max-height: 100%;*/
            /*--fc-time-grid-event_fc-content-overflow: hidden;*/
            /*--fc-time-grid-event_fc-time-font-size: 0.85em;*/
            /*--fc-time-grid-event_fc-time-padding: 0 1px;*/
            /*--fc-time-grid-event_fc-time-white-space: nowrap;*/
            /*--fc-time-grid-event_fc-title-padding: 0 1px;*/
            /*--fc-time-grid-eventfc-allow-mouse-resize_fc-resizer-bottom: 0;*/
            /*--fc-time-grid-eventfc-allow-mouse-resize_fc-resizer-cursor: s-resize;*/
            /*--fc-time-grid-eventfc-allow-mouse-resize_fc-resizer-font-family: monospace;*/
            /*--fc-time-grid-eventfc-allow-mouse-resize_fc-resizer-font-size: 11px;*/
            /*--fc-time-grid-eventfc-allow-mouse-resize_fc-resizer-height: 8px;*/
            /*--fc-time-grid-eventfc-allow-mouse-resize_fc-resizer-left: 0;*/
            /*--fc-time-grid-eventfc-allow-mouse-resize_fc-resizer-line-height: 8px;*/
            /*--fc-time-grid-eventfc-allow-mouse-resize_fc-resizer-overflow: hidden;*/
            /*--fc-time-grid-eventfc-allow-mouse-resize_fc-resizer-right: 0;*/
            /*--fc-time-grid-eventfc-allow-mouse-resize_fc-resizer-text-align: center;*/
            /*--fc-time-grid-eventfc-allow-mouse-resize_fc-resizer_COLON_after-content: "=";*/
            /*--fc-time-grid-eventfc-not-end-border-bottom-left-radius: 0;*/
            /*--fc-time-grid-eventfc-not-end-border-bottom-right-radius: 0;*/
            /*--fc-time-grid-eventfc-not-end-border-bottom-width: 0;*/
            /*--fc-time-grid-eventfc-not-end-padding-bottom: 1px;*/
            /*--fc-time-grid-eventfc-not-start-border-top-left-radius: 0;*/
            /*--fc-time-grid-eventfc-not-start-border-top-right-radius: 0;*/
            /*--fc-time-grid-eventfc-not-start-border-top-width: 0;*/
            /*--fc-time-grid-eventfc-not-start-padding-top: 1px;*/
            /*--fc-time-grid-eventfc-selected_fc-resizer-background: rgb(255, 255, 255);*/
            /*--fc-time-grid-eventfc-selected_fc-resizer-border-color: inherit;*/
            /*--fc-time-grid-eventfc-selected_fc-resizer-border-radius: 5px;*/
            /*--fc-time-grid-eventfc-selected_fc-resizer-border-style: solid;*/
            /*--fc-time-grid-eventfc-selected_fc-resizer-border-width: 1px;*/
            /*--fc-time-grid-eventfc-selected_fc-resizer-bottom: -5px;*/
            /*--fc-time-grid-eventfc-selected_fc-resizer-height: 8px;*/
            /*--fc-time-grid-eventfc-selected_fc-resizer-left: 50%;*/
            /*--fc-time-grid-eventfc-selected_fc-resizer-margin-left: -5px;*/
            /*--fc-time-grid-eventfc-selected_fc-resizer-width: 8px;*/
            /*--fc-time-grid-eventfc-short_fc-content-white-space: nowrap;*/
            /*--fc-time-grid-eventfc-short_fc-time-display: inline-block;*/
            /*--fc-time-grid-eventfc-short_fc-time-vertical-align: top;*/
            /*--fc-time-grid-eventfc-short_fc-time_COLON_after-content: " - ";*/
            /*--fc-time-grid-eventfc-short_fc-time_COLON_before-content: attr(data-start);*/
            /*--fc-time-grid-eventfc-short_fc-time_span-display: none;*/
            /*--fc-time-grid-eventfc-short_fc-title-display: inline-block;*/
            /*--fc-time-grid-eventfc-short_fc-title-font-size: 0.85em;*/
            /*--fc-time-grid-eventfc-short_fc-title-padding: 0;*/
            /*--fc-time-grid-eventfc-short_fc-title-vertical-align: top;*/
            /*--fc-time-grid-min-height: 100%;*/
            /*--fc-time-grid-position: relative;*/
            /*--fc-time-grid-z-index: 1;*/
            /*--fc-time-grid__LACE_BRACE__fc-bg-z-index: 1;*/
            /*--fc-time-grid__LACE_BRACE__hr-position: relative;*/
            /*--fc-time-grid__LACE_BRACE__hr-z-index: 2;*/
            /*--fc-time-grid_fc-bgevent-container-position: relative;*/
            /*--fc-time-grid_fc-bgevent-container-z-index: 2;*/
            /*--fc-time-grid_fc-bgevent-left: 0;*/
            /*--fc-time-grid_fc-bgevent-position: absolute;*/
            /*--fc-time-grid_fc-bgevent-right: 0;*/
            /*--fc-time-grid_fc-bgevent-z-index: 1;*/
            /*--fc-time-grid_fc-business-container-position: relative;*/
            /*--fc-time-grid_fc-business-container-z-index: 1;*/
            /*--fc-time-grid_fc-content-col-position: relative;*/
            /*--fc-time-grid_fc-content-skeleton-left: 0;*/
            /*--fc-time-grid_fc-content-skeleton-position: absolute;*/
            /*--fc-time-grid_fc-content-skeleton-right: 0;*/
            /*--fc-time-grid_fc-content-skeleton-top: 0;*/
            /*--fc-time-grid_fc-content-skeleton-z-index: 3;*/
            /*--fc-time-grid_fc-event-container-position: relative;*/
            /*--fc-time-grid_fc-event-container-z-index: 4;*/
            /*--fc-time-grid_fc-event-position: absolute;*/
            /*--fc-time-grid_fc-event-z-index: 1;*/
            /*--fc-time-grid_fc-highlight-container-position: relative;*/
            /*--fc-time-grid_fc-highlight-container-z-index: 3;*/
            /*--fc-time-grid_fc-highlight-left: 0;*/
            /*--fc-time-grid_fc-highlight-position: absolute;*/
            /*--fc-time-grid_fc-highlight-right: 0;*/
            /*--fc-time-grid_fc-mirror-container-position: relative;*/
            /*--fc-time-grid_fc-mirror-container-z-index: 6;*/
            /*--fc-time-grid_fc-now-indicator-arrow-margin-top: -5px;*/
            /*--fc-time-grid_fc-now-indicator-line-border-top-width: 1px;*/
            /*--fc-time-grid_fc-now-indicator-line-left: 0;*/
            /*--fc-time-grid_fc-now-indicator-line-right: 0;*/
            /*--fc-time-grid_fc-now-indicator-line-z-index: 5;*/
            /*--fc-time-grid_fc-slats-position: relative;*/
            /*--fc-time-grid_fc-slats-z-index: 2;*/
            /*--fc-time-grid_fc-slats_fc-minor_td-border-top-style: dotted;*/
            /*--fc-time-grid_fc-slats_td-border-bottom: 0;*/
            /*--fc-time-grid_fc-slats_td-height: 1.5em;*/
            /*--fc-time-grid_table-border: 0 hidden transparent;*/
            /*--fc-timeGrid-view_fc-day-grid-position: relative;*/
            /*--fc-timeGrid-view_fc-day-grid-z-index: 2;*/
            /*--fc-timeGrid-view_fc-day-grid_fc-row-min-height: 3em;*/
            /*--fc-timeGrid-view_fc-day-grid_fc-row_fc-content-skeleton-padding-bottom: 1em;*/
            /*--fc-toolbar-align-items: center;*/
            /*--fc-toolbar-display: flex;*/
            /*--fc-toolbar-justify-content: space-between;*/
            /*--fc-toolbar__LACE_BRACE___ASTERISK___LACE_BRACE___COLON_not_R_BRACKET_OPEN__COLON_first-child_R_BRACKET_CLOSE_-margin-left: 0.75em;*/
            /*--fc-toolbar_h2-font-size: 1.75em;*/
            /*--fc-toolbar_h2-margin: 0;*/
            /*--fc-toolbarfc-footer-toolbar-margin-top: 1.5em;*/
            /*--fc-toolbarfc-header-toolbar-margin-bottom: 1.5em;*/
            /*--fc-unselectable--khtml-user-select: none;*/
            /*--fc-unselectable--moz-user-select: none;*/
            /*--fc-unselectable--ms-user-select: none;*/
            /*--fc-unselectable--webkit-tap-highlight-color: rgba(0, 0, 0, 0);*/
            /*--fc-unselectable--webkit-touch-callout: none;*/
            /*--fc-unselectable--webkit-user-select: none;*/
            /*--fc-unselectable-user-select: none;*/
            /*--fc-unthemed_fc-content-border-color: rgb(221, 221, 221);*/
            /*--fc-unthemed_fc-disabled-day-background: rgb(215, 215, 215);*/
            /*--fc-unthemed_fc-disabled-day-opacity: 0.3;*/
            /*--fc-unthemed_fc-divider-background: rgb(238, 238, 238);*/
            /*--fc-unthemed_fc-divider-border-color: rgb(221, 221, 221);*/
            /*--fc-unthemed_fc-list-empty-background-color: rgb(238, 238, 238);*/

   //todo          --fc-unthemed_fc-list-heading_td-background: rgb(14,20,28);


            /*--fc-unthemed_fc-list-heading_td-border-color: rgb(221, 221, 221);*/
            /*--fc-unthemed_fc-list-item_COLON_hover_td-background-color: rgb(245, 245, 245);*/
            /*--fc-unthemed_fc-list-view-border-color: rgb(221, 221, 221);*/
            /*--fc-unthemed_fc-popover-background-color: rgb(255, 255, 255);*/
            /*--fc-unthemed_fc-popover-border-color: rgb(221, 221, 221);*/
            /*--fc-unthemed_fc-popover-border-style: solid;*/
            /*--fc-unthemed_fc-popover-border-width: 1px;*/
            /*--fc-unthemed_fc-popover_fc-header-background: rgb(238, 238, 238);*/
            /*--fc-unthemed_fc-row-border-color: rgb(221, 221, 221);*/
            /*--fc-unthemed_tbody-border-color: rgb(221, 221, 221);*/
            /*--fc-unthemed_td-border-color: rgb(221, 221, 221);*/
            /*--fc-unthemed_tdfc-today-background: red;*/
            /*--fc-unthemed_th-border-color: rgb(221, 221, 221);*/
            /*--fc-unthemed_thead-border-color: rgb(221, 221, 221);*/
            /*--fc-view-container-position: relative;*/
            /*--fc-view-container__ASTERISK_--moz-box-sizing: content-box;*/
            /*--fc-view-container__ASTERISK_--webkit-box-sizing: content-box;*/
            /*--fc-view-container__ASTERISK_-box-sizing: content-box;*/
            /*--fc-view-container__ASTERISK__COLON_after--moz-box-sizing: content-box;*/
            /*--fc-view-container__ASTERISK__COLON_after--webkit-box-sizing: content-box;*/
            /*--fc-view-container__ASTERISK__COLON_after-box-sizing: content-box;*/
            /*--fc-view-container__ASTERISK__COLON_before--moz-box-sizing: content-box;*/
            /*--fc-view-container__ASTERISK__COLON_before--webkit-box-sizing: content-box;*/
            /*--fc-view-container__ASTERISK__COLON_before-box-sizing: content-box;*/
            /*--fc-view-position: relative;*/
            /*--fc-view-z-index: 1;*/
            /*--fc-view__LACE_BRACE__table-position: relative;*/
            /*--fc-view__LACE_BRACE__table-z-index: 1;*/
            /*--fc_fc-axis-padding: 0 4px;*/
            /*--fc_fc-axis-vertical-align: middle;*/
            /*--fc_fc-axis-white-space: nowrap;*/
            /*--fc_fc-list-table-table-layout: auto;*/
            /*--fc_fc-row-border-style: solid;*/
            /*--fc_fc-row-border-width: 0;*/
            /*--fc_fc-row_fc-content-skeleton_table-background: none;*/
            /*--fc_fc-row_fc-content-skeleton_table-border-color: transparent;*/
            /*--fc_fc-row_fc-content-skeleton_td-background: none;*/
            /*--fc_fc-row_fc-content-skeleton_td-border-color: transparent;*/
            /*--fc_fc-row_fc-mirror-skeleton_td-background: none;*/
            /*--fc_fc-row_fc-mirror-skeleton_td-border-color: transparent;*/
            /*--fc_table-border-collapse: collapse;*/
            /*--fc_table-border-spacing: 0;*/
            /*--fc_table-box-sizing: border-box;*/
            /*--fc_table-font-size: 0.5em;*/
            /*--fc_table-table-layout: fixed;*/
            /*--fc_table-width: 100%;*/
            /*--fc_td-border-style: solid;*/
            /*--fc_td-border-width: 1px;*/
            /*--fc_td-padding: 0;*/
            /*--fc_td-vertical-align: top;*/



// not working    --fc_tdfc-today-border-style: double;


            /*--fc_th-border-style: solid;*/
            /*--fc_th-border-width: 1px;*/
            /*--fc_th-padding: 0;*/
            /*--fc_th-text-align: center;*/
            /*--fc_th-vertical-align: top;*/
            /*--hrfc-divider-border-width: 1px 0;*/
            /*--hrfc-divider-height: 0;*/
            /*--hrfc-divider-margin: 0;*/
            /*--hrfc-divider-padding: 0 0 2px;*/
            /*--tr_COLON_first-child__LACE_BRACE__td__LACE_BRACE__fc-day-grid-event-margin-top: 2px;*/



/* light blue to be used instead of default light yellow*/
--fc-unthemed_tdfc-today-background: #90EE90 !important;




//

/*
--fc-unthemed_tdfc-today-border-style: dashed; !important;
--fc-unthemed_tdfc-today-border-width: 5px !important;
*/

/*--fc_table-font-size: 1em;*/





        }
    </style>
</custom-style>
`;
document.head.appendChild($_documentContainer.content);