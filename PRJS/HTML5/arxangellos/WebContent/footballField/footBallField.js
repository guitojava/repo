

Ext.onReady(function() {

	
	team1 = new Array();

	var g = getUrlParam('g');
	var gt = getUrlParam('gtp');
	var e = getUrlParam('e');

	// alert ( " g=" + g + " gt=" +gt + " e=" + e ) ;

	if (g != "null" && gt != "null" && e != "null") {

		Ext.Ajax.request({
					method : "POST",
					url : PublicServlet ,
					params : {
						action : 'getFootbalPositions',
						g : g,
						gt : gt
					},
					scope : this,
					success : function(res) {

						var obj = Ext.util.JSON.decode(res.responseText);
						loadField(obj);

					}// success
				});// Ext.Ajax.request

	} else {

		// alert ( "initPositions ");
		//alert(" not initialized correctly  ");
		// initPositions( 5 );
		// loadIt();
	}

	// functions ------------

	function loadField(tdata) {

		var saveBtn = new Ext.Button({
					text : '<b> αποθήκευση σύνθεση παικτών στο γήπεδο <b>',
					// iconCls : 'icon-save',
					width : 300,
					renderTo : 'saveBtnDiv',
					scale : 'large',
					handler : function() {
						savePositions();
					}// handler
				});

		for (var i = 0; i < tdata.length; i++) {
			var player = new Object;
			player.pid = tdata[i].pid;

			player.x = tdata[i].x;
			player.y = tdata[i].y;

			if (  	tdata[i].pimg =='/css/logo/logo.png' || 
					tdata[i].pimg == '/mipClubs/css/logo/logo.png' ){
			
				player.pimg = footballFieldPlayerDefaultImage ; //make transparant
			}
			else {
				player.pimg = tdata[i].pimg;
				
			}
			
			
			
			// store
			team1.push(player);
			var divTag = createPlayerDiv(player);

			// alert ( "player.pid =" + player.pid );

			if (player.pid != "-1" || player.pid != -1)
				Ext.getDom("players").appendChild(divTag);

		}

		loadPlayers();

		
		
		if (e != '1')
			saveBtn.disable();

	}

	
	
	function createPlayerDiv(player) {
		var divTag = document.createElement("div");

		// var timestamp = new Date().getTime();
		// alert ( timestamp );

		divTag.id = player.pid;

		// divTag.setAttribute("align","center");
		// divTag.style.margin = "0px auto";
		// divTag.className ="dynamicDiv";

		// alert ( player.pimg + player.pid );

		if (player.pid != '-1' && player.pimg != null) {

			divTag.innerHTML = 
			"<p  style=' text-align:center; ' > " +
						"<img  class='imgThumb' align='top' src='" 	+ player.pimg 	+ "' /> " +
								" <br>"					+ 
						" <span style=' " +
						"   text-align:center;  " +
						"   color: #F2F2F2;  " +
						"   background-color: #029834;   " +
						"   font-size: 16px;" +
						"   font-family: verdana;   ' >"	+ player.pid + "</span>" +
			" </p>";

		}

		// img/player1.gif

		return divTag;
	}

	
	
	
	function loadPlayers() {

		Ext.QuickTips.init();

		// A list of method overrides
		var overrides = {
			// Called when mousedown for a specific amount of time
			b4StartDrag : function() {

				if (this.el == null) {
					this.el = Ext.get(this.getEl());
				}

				// this.el.highlight();
				// Cache the original XY Coordinates of the element, we'll use
				// this later.
				// this.originalXY = this.el.getXY();

			},
			// Called when element is dropped not anything other than a
			// dropzone with the same ddgroup
			onInvalidDrop : function() {
				// this.invalidDrop = true;

			},
			endDrag : function() {

				/*
				 * alert ( "New point pid: " + this.el.id + " x:"+
				 * this.el.getXY()[0] + " y:"+ this.el.getXY()[1] );
				 */
				
				for (var i = 0; i < team1.length; i++) {

					if (this.el != null && this.el.id != null
							&& team1[i].pid == this.el.id) {
						team1[i].x = this.el.getXY()[0];
						team1[i].y = this.el.getXY()[1];
					}

				}

			

			}

		};

		
		
		
		
		
		// Configure the players to be draggable
		var playersElems = Ext.get('players').select('div');
		Ext.each(playersElems.elements, function(el) {

					if (el != null && e == "1") {
						var dd = new Ext.dd.DD(el, 'playersDDGroup', {
									isTarget : false
								});
						Ext.apply(dd, overrides);
					}
		});

		
		var playersDDTarget = new Ext.dd.DDTarget('players', 'playersDDGroup');

		// Instantiate instances of Ext.dd.DDTarget for the players and trucks
		// container


		movePlayersIntoPlay();

	}
	
	
	
	function movePlayersIntoPlay() {

			for (var i = 0; i < team1.length; i++) {

				var player = new Object;
				player.pid = team1[i].pid;

				player.x = team1[i].x;
				player.y = team1[i].y;

				if (player.pid != '-1' || player.pid != -1) {

					var plElem = Ext.get(player.pid);
					// move to X.Y

					var animCfgObj = {
						easing : 'elasticOut',
						duration : 1,
						scope : this,
						callback : function() {
							// this.el.dom.style.position = '';
						}
					};

					var winW;
					var winH;
					if (parseInt(navigator.appVersion) > 3) {

						if (navigator.appName.indexOf("Microsoft") != -1) {
							winW = document.body.offsetWidth;
							winH = document.body.offsetHeight;
						}

						else {
							winW = window.innerWidth;
							winH = window.innerHeight;
						}

					}

					if (player.x == 50 && player.y == 50) {
						// recenter it to true middle of window
						player.x = winW / 2;
						player.y = 150;
					}

					/*
					 * document.write( "Window width = "+winW+"<br>" +"Window
					 * height = "+winH )
					 */

					if (plElem) plElem.moveTo(player.x, player.y);
					// plElem.moveTo(player.x, player.y, animCfgObj);

				}

			}

		}
	
	
	
	
	
	
	
	
	// helpers

	function savePositions() {

		if (e == "1") {

			var ret = "[";
			for (var i = 0; i < team1.length; i++) {
				var p = team1[i];

				var xInt = parseInt(p.x);
				var yInt = parseInt(p.y);

				if (i != team1.length - 1) {
					ret = ret + "{pid:'" + team1[i].pid + "',  x:" + xInt
							+ ",y:" + yInt + ",pimg:'" + p.pimg + "'   } "
							+ " , ";
				} else {
					ret = ret + "{pid:'" + team1[i].pid + "',  x:" + xInt
							+ ",y:" + yInt + ",pimg:'" + p.pimg + "'   } ";
				}
			}
			ret = ret + "]";

			// alert ( "g="+g+ "gt="+ gt + " ret = "+ ret);

			Ext.Ajax.request({
						method : "POST",
						url : GamesServlet ,
						params : {
							action : 'saveFootbalPositions',
							gt : gt,
							g : g,
							jsonFldPos : ret

						},
						scope : this,
						success : function(res) {

							/*
							 * Ext.Msg.show({ x:0, title : 'Saved changes ok',
							 * buttons : Ext.MessageBox.OK, msg : '<b>All
							 * changes saved</b> '
							 * 
							 * });
							 */

						}// success
					});// Ext.Ajax.request

		} else {

			Ext.Msg.show({
						title : 'Player positions',
						buttons : Ext.MessageBox.OK,
						msg : 'Only Game Organiser can change the Player positions.'

					});

		}

	}

	function initPositions(tot) {
		var xTemp = 100;
		var yTemp = 40;

		e = "1";

		for (var i = 0; i < tot; i++) {
			var player = new Object;

			player.pid = "#" + i;

			xTemp = xTemp + 70;

			if (i % 5 == 0) {
				yTemp = yTemp + 40;
				xTemp = 100;
			}

			player.x = xTemp;
			player.y = yTemp;

			// store
			team1.push(player);

			var divTag = createPlayerDiv(player);

			// alert ( "player.pid =" + player.pid );

			if (player.pid != "-1" || player.pid != -1)
				Ext.getDom("players").appendChild(divTag);

		}

	}

	function getUrlParam(name) {
		// get parameter from url
		name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
		var regexS = "[\\?&]" + name + "=([^&#]*)";
		var regex = new RegExp(regexS);
		var results = regex.exec(window.location.href);
		if (results == null)
			return "null";
		else
			return results[1];
	}

		// ----------------------

});// end

