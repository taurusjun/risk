(self.webpackChunkant_design_pro=self.webpackChunkant_design_pro||[]).push([[310],{64335:function(Te,X,d){"use strict";var q=d(67294),_=(0,q.createContext)({});X.Z=_},38310:function(Te,X,d){"use strict";d.d(X,{ZP:function(){return Ht}});var q=d(38663),_=d(70883),R=d(22122),A=d(96156),Be=d(6610),Oe=d(5991),Se=d(10379),we=d(44144),ee=d(90484),n=d(67294),Ae=d(94184),S=d.n(Ae),Fe=d(98423),Y=d(48717),I=d(86032),te=d(85061),re=d(75164);function Me(o){var e,r=function(i){return function(){e=null,o.apply(void 0,(0,te.Z)(i))}},t=function(){if(e==null){for(var i=arguments.length,s=new Array(i),c=0;c<i;c++)s[c]=arguments[c];e=(0,re.Z)(r(s))}};return t.cancel=function(){return re.Z.cancel(e)},t}function ae(){return function(e,r,t){var a=t.value,i=!1;return{configurable:!0,get:function(){if(i||this===e.prototype||this.hasOwnProperty(r))return a;var c=Me(a.bind(this));return i=!0,Object.defineProperty(this,r,{value:c,configurable:!0,writable:!0}),i=!1,c}}}}var He=d(64019);function U(o){return o!==window?o.getBoundingClientRect():{top:0,bottom:window.innerHeight}}function ne(o,e,r){if(r!==void 0&&e.top>o.top-r)return r+e.top}function oe(o,e,r){if(r!==void 0&&e.bottom<o.bottom+r){var t=window.innerHeight-e.bottom;return r+t}}var ie=["resize","scroll","touchstart","touchmove","touchend","pageshow","load"],H=[];function Vt(){return H}function le(o,e){if(!!o){var r=H.find(function(t){return t.target===o});r?r.affixList.push(e):(r={target:o,affixList:[e],eventHandlers:{}},H.push(r),ie.forEach(function(t){r.eventHandlers[t]=(0,He.Z)(o,t,function(){r.affixList.forEach(function(a){a.lazyUpdatePosition()})})}))}}function ce(o){var e=H.find(function(r){var t=r.affixList.some(function(a){return a===o});return t&&(r.affixList=r.affixList.filter(function(a){return a!==o})),t});e&&e.affixList.length===0&&(H=H.filter(function(r){return r!==e}),ie.forEach(function(r){var t=e.eventHandlers[r];t&&t.remove&&t.remove()}))}var se=function(o,e,r,t){var a=arguments.length,i=a<3?e:t===null?t=Object.getOwnPropertyDescriptor(e,r):t,s;if((typeof Reflect=="undefined"?"undefined":(0,ee.Z)(Reflect))==="object"&&typeof Reflect.decorate=="function")i=Reflect.decorate(o,e,r,t);else for(var c=o.length-1;c>=0;c--)(s=o[c])&&(i=(a<3?s(i):a>3?s(e,r,i):s(e,r))||i);return a>3&&i&&Object.defineProperty(e,r,i),i};function De(){return typeof window!="undefined"?window:null}var D;(function(o){o[o.None=0]="None",o[o.Prepare=1]="Prepare"})(D||(D={}));var W=function(o){(0,Se.Z)(r,o);var e=(0,we.Z)(r);function r(){var t;return(0,Be.Z)(this,r),t=e.apply(this,arguments),t.state={status:D.None,lastAffix:!1,prevTarget:null},t.getOffsetTop=function(){var a=t.props,i=a.offsetBottom,s=a.offsetTop;return i===void 0&&s===void 0?0:s},t.getOffsetBottom=function(){return t.props.offsetBottom},t.savePlaceholderNode=function(a){t.placeholderNode=a},t.saveFixedNode=function(a){t.fixedNode=a},t.measure=function(){var a=t.state,i=a.status,s=a.lastAffix,c=t.props.onChange,u=t.getTargetFunc();if(!(i!==D.Prepare||!t.fixedNode||!t.placeholderNode||!u)){var f=t.getOffsetTop(),m=t.getOffsetBottom(),v=u();if(!!v){var l={status:D.None},g=U(v),h=U(t.placeholderNode),b=ne(h,g,f),C=oe(h,g,m);b!==void 0?(l.affixStyle={position:"fixed",top:b,width:h.width,height:h.height},l.placeholderStyle={width:h.width,height:h.height}):C!==void 0&&(l.affixStyle={position:"fixed",bottom:C,width:h.width,height:h.height},l.placeholderStyle={width:h.width,height:h.height}),l.lastAffix=!!l.affixStyle,c&&s!==l.lastAffix&&c(l.lastAffix),t.setState(l)}}},t.prepareMeasure=function(){if(t.setState({status:D.Prepare,affixStyle:void 0,placeholderStyle:void 0}),!1)var a},t}return(0,Oe.Z)(r,[{key:"getTargetFunc",value:function(){var a=this.context.getTargetContainer,i=this.props.target;return i!==void 0?i:a||De}},{key:"componentDidMount",value:function(){var a=this,i=this.getTargetFunc();i&&(this.timeout=setTimeout(function(){le(i(),a),a.updatePosition()}))}},{key:"componentDidUpdate",value:function(a){var i=this.state.prevTarget,s=this.getTargetFunc(),c=(s==null?void 0:s())||null;i!==c&&(ce(this),c&&(le(c,this),this.updatePosition()),this.setState({prevTarget:c})),(a.offsetTop!==this.props.offsetTop||a.offsetBottom!==this.props.offsetBottom)&&this.updatePosition(),this.measure()}},{key:"componentWillUnmount",value:function(){clearTimeout(this.timeout),ce(this),this.updatePosition.cancel(),this.lazyUpdatePosition.cancel()}},{key:"updatePosition",value:function(){this.prepareMeasure()}},{key:"lazyUpdatePosition",value:function(){var a=this.getTargetFunc(),i=this.state.affixStyle;if(a&&i){var s=this.getOffsetTop(),c=this.getOffsetBottom(),u=a();if(u&&this.placeholderNode){var f=U(u),m=U(this.placeholderNode),v=ne(m,f,s),l=oe(m,f,c);if(v!==void 0&&i.top===v||l!==void 0&&i.bottom===l)return}}this.prepareMeasure()}},{key:"render",value:function(){var a=this,i=this.context.getPrefixCls,s=this.state,c=s.affixStyle,u=s.placeholderStyle,f=this.props,m=f.prefixCls,v=f.children,l=S()((0,A.Z)({},i("affix",m),!!c)),g=(0,Fe.Z)(this.props,["prefixCls","offsetTop","offsetBottom","target","onChange"]);return n.createElement(Y.Z,{onResize:function(){a.updatePosition()}},n.createElement("div",(0,R.Z)({},g,{ref:this.savePlaceholderNode}),c&&n.createElement("div",{style:u,"aria-hidden":"true"}),n.createElement("div",{className:l,ref:this.saveFixedNode,style:c},n.createElement(Y.Z,{onResize:function(){a.updatePosition()}},v))))}}]),r}(n.Component);W.contextType=I.E_,se([ae()],W.prototype,"updatePosition",null),se([ae()],W.prototype,"lazyUpdatePosition",null);var Le=W,Jt=d(84305),k=d(73893),Qt=d(59903),qt=d(81262),_t=d(30887),er=d(59250),tr=d(94233),de=d(28481),p=d(28991),Ie={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M872 474H286.9l350.2-304c5.6-4.9 2.2-14-5.2-14h-88.5c-3.9 0-7.6 1.4-10.5 3.9L155 487.8a31.96 31.96 0 000 48.3L535.1 866c1.5 1.3 3.3 2 5.2 2h91.5c7.4 0 10.8-9.2 5.2-14L286.9 550H872c4.4 0 8-3.6 8-8v-60c0-4.4-3.6-8-8-8z"}}]},name:"arrow-left",theme:"outlined"},ze=Ie,ue=d(27029),fe=function(e,r){return n.createElement(ue.Z,(0,p.Z)((0,p.Z)({},e),{},{ref:r,icon:ze}))};fe.displayName="ArrowLeftOutlined";var $e=n.forwardRef(fe),je={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M869 487.8L491.2 159.9c-2.9-2.5-6.6-3.9-10.5-3.9h-88.5c-7.4 0-10.8 9.2-5.2 14l350.2 304H152c-4.4 0-8 3.6-8 8v60c0 4.4 3.6 8 8 8h585.1L386.9 854c-5.6 4.9-2.2 14 5.2 14h91.5c1.9 0 3.8-.7 5.2-2L869 536.2a32.07 32.07 0 000-48.4z"}}]},name:"arrow-right",theme:"outlined"},Ue=je,ve=function(e,r){return n.createElement(ue.Z,(0,p.Z)((0,p.Z)({},e),{},{ref:r,icon:Ue}))};ve.displayName="ArrowRightOutlined";var We=n.forwardRef(ve),ke=d(50344),Ke=d(57254),Ge=d(65514),Xe=function(o,e){var r={};for(var t in o)Object.prototype.hasOwnProperty.call(o,t)&&e.indexOf(t)<0&&(r[t]=o[t]);if(o!=null&&typeof Object.getOwnPropertySymbols=="function")for(var a=0,t=Object.getOwnPropertySymbols(o);a<t.length;a++)e.indexOf(t[a])<0&&Object.prototype.propertyIsEnumerable.call(o,t[a])&&(r[t[a]]=o[t[a]]);return r},me=function(e){var r=e.prefixCls,t=e.separator,a=t===void 0?"/":t,i=e.children,s=e.overlay,c=e.dropdownProps,u=Xe(e,["prefixCls","separator","children","overlay","dropdownProps"]),f=n.useContext(I.E_),m=f.getPrefixCls,v=m("breadcrumb",r),l=function(b){return s?n.createElement(Ge.Z,(0,R.Z)({overlay:s,placement:"bottomCenter"},c),n.createElement("span",{className:"".concat(v,"-overlay-link")},b,n.createElement(Ke.Z,null))):b},g;return"href"in u?g=n.createElement("a",(0,R.Z)({className:"".concat(v,"-link")},u),i):g=n.createElement("span",(0,R.Z)({className:"".concat(v,"-link")},u),i),g=l(g),i?n.createElement("span",null,g,a&&n.createElement("span",{className:"".concat(v,"-separator")},a)):null};me.__ANT_BREADCRUMB_ITEM=!0;var he=me,ge=function(e){var r=e.children,t=n.useContext(I.E_),a=t.getPrefixCls,i=a("breadcrumb");return n.createElement("span",{className:"".concat(i,"-separator")},r||"/")};ge.__ANT_BREADCRUMB_SEPARATOR=!0;var Ye=ge,be=d(54689),Ve=d(21687),Je=d(96159),Qe=function(o,e){var r={};for(var t in o)Object.prototype.hasOwnProperty.call(o,t)&&e.indexOf(t)<0&&(r[t]=o[t]);if(o!=null&&typeof Object.getOwnPropertySymbols=="function")for(var a=0,t=Object.getOwnPropertySymbols(o);a<t.length;a++)e.indexOf(t[a])<0&&Object.prototype.propertyIsEnumerable.call(o,t[a])&&(r[t[a]]=o[t[a]]);return r};function qe(o,e){if(!o.breadcrumbName)return null;var r=Object.keys(e).join("|"),t=o.breadcrumbName.replace(new RegExp(":(".concat(r,")"),"g"),function(a,i){return e[i]||a});return t}function _e(o,e,r,t){var a=r.indexOf(o)===r.length-1,i=qe(o,e);return a?n.createElement("span",null,i):n.createElement("a",{href:"#/".concat(t.join("/"))},i)}var ye=function(e,r){return e=(e||"").replace(/^\//,""),Object.keys(r).forEach(function(t){e=e.replace(":".concat(t),r[t])}),e},et=function(e,r,t){var a=(0,te.Z)(e),i=ye(r||"",t);return i&&a.push(i),a},V=function(e){var r=e.prefixCls,t=e.separator,a=t===void 0?"/":t,i=e.style,s=e.className,c=e.routes,u=e.children,f=e.itemRender,m=f===void 0?_e:f,v=e.params,l=v===void 0?{}:v,g=Qe(e,["prefixCls","separator","style","className","routes","children","itemRender","params"]),h=n.useContext(I.E_),b=h.getPrefixCls,C=h.direction,x,P=b("breadcrumb",r);if(c&&c.length>0){var E=[];x=c.map(function(y){var N=ye(y.path,l);N&&E.push(N);var B;return y.children&&y.children.length&&(B=n.createElement(be.Z,null,y.children.map(function(Z){return n.createElement(be.Z.Item,{key:Z.path||Z.breadcrumbName},m(Z,l,c,et(E,Z.path,l)))}))),n.createElement(he,{overlay:B,separator:a,key:N||y.breadcrumbName},m(y,l,c,E))})}else u&&(x=(0,ke.Z)(u).map(function(y,N){return y&&((0,Ve.Z)(y.type&&(y.type.__ANT_BREADCRUMB_ITEM===!0||y.type.__ANT_BREADCRUMB_SEPARATOR===!0),"Breadcrumb","Only accepts Breadcrumb.Item and Breadcrumb.Separator as it's children"),(0,Je.Tm)(y,{separator:a,key:N}))}));var O=S()(P,(0,A.Z)({},"".concat(P,"-rtl"),C==="rtl"),s);return n.createElement("div",(0,R.Z)({className:O,style:i},g),x)};V.Item=he,V.Separator=Ye;var tt=V,rt=tt,at=d(51890),xe=d(15105),nt=function(o,e){var r={};for(var t in o)Object.prototype.hasOwnProperty.call(o,t)&&e.indexOf(t)<0&&(r[t]=o[t]);if(o!=null&&typeof Object.getOwnPropertySymbols=="function")for(var a=0,t=Object.getOwnPropertySymbols(o);a<t.length;a++)e.indexOf(t[a])<0&&Object.prototype.propertyIsEnumerable.call(o,t[a])&&(r[t[a]]=o[t[a]]);return r},ot={border:0,background:"transparent",padding:0,lineHeight:"inherit",display:"inline-block"},it=n.forwardRef(function(o,e){var r=function(m){var v=m.keyCode;v===xe.Z.ENTER&&m.preventDefault()},t=function(m){var v=m.keyCode,l=o.onClick;v===xe.Z.ENTER&&l&&l()},a=o.style,i=o.noStyle,s=o.disabled,c=nt(o,["style","noStyle","disabled"]),u={};return i||(u=(0,R.Z)({},ot)),s&&(u.pointerEvents="none"),u=(0,R.Z)((0,R.Z)({},u),a),n.createElement("div",(0,R.Z)({role:"button",tabIndex:0,ref:e},c,{onKeyDown:r,onKeyUp:t,style:u}))}),lt=it,ct=d(42051);function st(){var o=n.useRef(!0);return n.useEffect(function(){return function(){o.current=!1}},[]),function(){return!o.current}}var dt=function(e,r,t){return!r||!t?null:n.createElement(ct.Z,{componentName:"PageHeader"},function(a){var i=a.back;return n.createElement("div",{className:"".concat(e,"-back")},n.createElement(lt,{onClick:function(c){t==null||t(c)},className:"".concat(e,"-back-button"),"aria-label":i},r))})},ut=function(e){return n.createElement(rt,e)},ft=function(e){var r=arguments.length>1&&arguments[1]!==void 0?arguments[1]:"ltr";return e.backIcon!==void 0?e.backIcon:r==="rtl"?n.createElement(We,null):n.createElement($e,null)},vt=function(e,r){var t=arguments.length>2&&arguments[2]!==void 0?arguments[2]:"ltr",a=r.title,i=r.avatar,s=r.subTitle,c=r.tags,u=r.extra,f=r.onBack,m="".concat(e,"-heading"),v=a||s||c||u;if(!v)return null;var l=ft(r,t),g=dt(e,l,f),h=g||i||v;return n.createElement("div",{className:m},h&&n.createElement("div",{className:"".concat(m,"-left")},g,i&&n.createElement(at.C,i),a&&n.createElement("span",{className:"".concat(m,"-title"),title:typeof a=="string"?a:void 0},a),s&&n.createElement("span",{className:"".concat(m,"-sub-title"),title:typeof s=="string"?s:void 0},s),c&&n.createElement("span",{className:"".concat(m,"-tags")},c)),u&&n.createElement("span",{className:"".concat(m,"-extra")},u))},mt=function(e,r){return r?n.createElement("div",{className:"".concat(e,"-footer")},r):null},ht=function(e,r){return n.createElement("div",{className:"".concat(e,"-content")},r)},gt=function(e){var r=n.useState(!1),t=(0,de.Z)(r,2),a=t[0],i=t[1],s=st(),c=function(f){var m=f.width;s()||i(m<768)};return n.createElement(I.C,null,function(u){var f,m=u.getPrefixCls,v=u.pageHeader,l=u.direction,g,h=e.prefixCls,b=e.style,C=e.footer,x=e.children,P=e.breadcrumb,E=e.breadcrumbRender,O=e.className,y=!0;"ghost"in e?y=e.ghost:v&&"ghost"in v&&(y=v.ghost);var N=m("page-header",h),B=function(){var L;return((L=P)===null||L===void 0?void 0:L.routes)?ut(P):null},Z=B(),F=P&&"props"in P,T=(g=E==null?void 0:E(e,Z))!==null&&g!==void 0?g:Z,z=F?P:T,G=S()(N,O,(f={"has-breadcrumb":!!z,"has-footer":!!C},(0,A.Z)(f,"".concat(N,"-ghost"),y),(0,A.Z)(f,"".concat(N,"-rtl"),l==="rtl"),(0,A.Z)(f,"".concat(N,"-compact"),a),f));return n.createElement(Y.Z,{onResize:c},n.createElement("div",{className:G,style:b},z,vt(N,e,l),x&&ht(N,x),mt(N,C)))})},bt=gt,J=d(81253),rr=d(18106),Ce=d(90642),K=d(64335),ar=d(53645),yt=function(e){var r=(0,n.useContext)(K.Z),t=e.children,a=e.contentWidth,i=e.className,s=e.style,c=(0,n.useContext)(k.ZP.ConfigContext),u=c.getPrefixCls,f=e.prefixCls||u("pro"),m=a||r.contentWidth,v="".concat(f,"-grid-content");return n.createElement("div",{className:S()(v,i,{wide:m==="Fixed"}),style:s},n.createElement("div",{className:"".concat(f,"-grid-content-children")},t))},xt=yt,Ct=d(97435),nr=d(56264),pt=["children","className","extra","style","renderContent"],Pt=function(e){var r=e.children,t=e.className,a=e.extra,i=e.style,s=e.renderContent,c=(0,J.Z)(e,pt),u=(0,n.useContext)(k.ZP.ConfigContext),f=u.getPrefixCls,m=e.prefixCls||f("pro"),v="".concat(m,"-footer-bar"),l=(0,n.useContext)(K.Z),g=(0,n.useMemo)(function(){var b=l.hasSiderMenu,C=l.isMobile,x=l.siderWidth;if(!!b)return x?C?"100%":"calc(100% - ".concat(x,"px)"):"100%"},[l.collapsed,l.hasSiderMenu,l.isMobile,l.siderWidth]),h=n.createElement(n.Fragment,null,n.createElement("div",{className:"".concat(v,"-left")},a),n.createElement("div",{className:"".concat(v,"-right")},r));return(0,n.useEffect)(function(){return!l||!(l==null?void 0:l.setHasFooterToolbar)?function(){}:(l==null||l.setHasFooterToolbar(!0),function(){var b;l==null||(b=l.setHasFooterToolbar)===null||b===void 0||b.call(l,!1)})},[]),n.createElement("div",(0,R.Z)({className:S()(t,"".concat(v)),style:(0,p.Z)({width:g},i)},(0,Ct.Z)(c,["prefixCls"])),s?s((0,p.Z)((0,p.Z)((0,p.Z)({},e),l),{},{leftWidth:g}),h):h)},Et=Pt,or=d(12395),Nt=d(83832),Rt=function(e){if(!e)return 1;var r=e.backingStorePixelRatio||e.webkitBackingStorePixelRatio||e.mozBackingStorePixelRatio||e.msBackingStorePixelRatio||e.oBackingStorePixelRatio||e.backingStorePixelRatio||1;return(window.devicePixelRatio||1)/r},Zt=function(e){var r=e.children,t=e.style,a=e.className,i=e.markStyle,s=e.markClassName,c=e.zIndex,u=c===void 0?9:c,f=e.gapX,m=f===void 0?212:f,v=e.gapY,l=v===void 0?222:v,g=e.width,h=g===void 0?120:g,b=e.height,C=b===void 0?64:b,x=e.rotate,P=x===void 0?-22:x,E=e.image,O=e.content,y=e.offsetLeft,N=e.offsetTop,B=e.fontStyle,Z=B===void 0?"normal":B,F=e.fontWeight,T=F===void 0?"normal":F,z=e.fontColor,G=z===void 0?"rgba(0,0,0,.15)":z,Q=e.fontSize,L=Q===void 0?16:Q,pe=e.fontFamily,Pe=pe===void 0?"sans-serif":pe,Dt=e.prefixCls,Lt=(0,n.useContext)(k.ZP.ConfigContext),It=Lt.getPrefixCls,Ee=It("pro-layout-watermark",Dt),zt=S()("".concat(Ee,"-wrapper"),a),$t=S()(Ee,s),jt=(0,n.useState)(""),Ne=(0,de.Z)(jt,2),Ut=Ne[0],Re=Ne[1];return(0,n.useEffect)(function(){var $=document.createElement("canvas"),w=$.getContext("2d"),M=Rt(w),Wt="".concat((m+h)*M,"px"),kt="".concat((l+C)*M,"px"),Kt=y||m/2,Gt=N||l/2;if($.setAttribute("width",Wt),$.setAttribute("height",kt),w){w.translate(Kt*M,Gt*M),w.rotate(Math.PI/180*Number(P));var Xt=h*M,Ze=C*M;if(E){var j=new Image;j.crossOrigin="anonymous",j.referrerPolicy="no-referrer",j.src=E,j.onload=function(){w.drawImage(j,0,0,Xt,Ze),Re($.toDataURL())}}else if(O){var Yt=Number(L)*M;w.font="".concat(Z," normal ").concat(T," ").concat(Yt,"px/").concat(Ze,"px ").concat(Pe),w.fillStyle=G,w.fillText(O,0,0),Re($.toDataURL())}}else console.error("\u5F53\u524D\u73AF\u5883\u4E0D\u652F\u6301Canvas")},[m,l,y,N,P,Z,T,h,C,Pe,G,E,O,L]),n.createElement("div",{style:(0,p.Z)({position:"relative"},t),className:zt},r,n.createElement("div",{className:$t,style:(0,p.Z)({zIndex:u,position:"absolute",left:0,top:0,width:"100%",height:"100%",backgroundSize:"".concat(m+h,"px"),pointerEvents:"none",backgroundRepeat:"repeat",backgroundImage:"url('".concat(Ut,"')")},i)}))},Tt=Zt,Bt=["title","content","pageHeaderRender","header","prefixedClassName","extraContent","style","prefixCls","breadcrumbRender"],Ot=["children","loading","className","style","footer","affixProps","ghost","fixedHeader","breadcrumbRender"];function St(o){return(0,ee.Z)(o)==="object"?o:{spinning:o}}var wt=function(e){var r=e.tabList,t=e.tabActiveKey,a=e.onTabChange,i=e.tabBarExtraContent,s=e.tabProps,c=e.prefixedClassName;return Array.isArray(r)||i?n.createElement(Ce.Z,(0,R.Z)({className:"".concat(c,"-tabs"),activeKey:t,onChange:function(f){a&&a(f)},tabBarExtraContent:i},s),r==null?void 0:r.map(function(u,f){return n.createElement(Ce.Z.TabPane,(0,R.Z)({},u,{tab:u.tab,key:u.key||f}))})):null},At=function(e,r,t){return!e&&!r?null:n.createElement("div",{className:"".concat(t,"-detail")},n.createElement("div",{className:"".concat(t,"-main")},n.createElement("div",{className:"".concat(t,"-row")},e&&n.createElement("div",{className:"".concat(t,"-content")},e),r&&n.createElement("div",{className:"".concat(t,"-extraContent")},r))))},ir=function(e){var r=useContext(RouteContext);return React.createElement("div",{style:{height:"100%",display:"flex",alignItems:"center"}},React.createElement(_Breadcrumb,_extends({},r==null?void 0:r.breadcrumb,r==null?void 0:r.breadcrumbProps,e)))},Ft=function(e){var r,t=(0,n.useContext)(K.Z),a=e.title,i=e.content,s=e.pageHeaderRender,c=e.header,u=e.prefixedClassName,f=e.extraContent,m=e.style,v=e.prefixCls,l=e.breadcrumbRender,g=(0,J.Z)(e,Bt),h=(0,n.useMemo)(function(){if(!!l)return l},[l]);if(s===!1)return null;if(s)return n.createElement(n.Fragment,null," ",s((0,p.Z)((0,p.Z)({},e),t)));var b=a;!a&&a!==!1&&(b=t.title);var C=(0,p.Z)((0,p.Z)((0,p.Z)({},t),{},{title:b},g),{},{footer:wt((0,p.Z)((0,p.Z)({},g),{},{breadcrumbRender:l,prefixedClassName:u}))},c),x=C.breadcrumb,P=(!x||!(x==null?void 0:x.itemRender)&&!(x==null||(r=x.routes)===null||r===void 0?void 0:r.length))&&!l;return["title","subTitle","extra","tags","footer","avatar","backIcon"].every(function(E){return!C[E]})&&P&&!i&&!f?null:n.createElement("div",{className:"".concat(u,"-warp")},n.createElement(bt,(0,R.Z)({},C,{breadcrumb:l===!1?void 0:(0,p.Z)((0,p.Z)({},C.breadcrumb),t.breadcrumbProps),breadcrumbRender:h,prefixCls:v}),(c==null?void 0:c.children)||At(i,f,u)))},Mt=function(e){var r,t,a=e.children,i=e.loading,s=i===void 0?!1:i,c=e.className,u=e.style,f=e.footer,m=e.affixProps,v=e.ghost,l=e.fixedHeader,g=e.breadcrumbRender,h=(0,J.Z)(e,Ot),b=(0,n.useContext)(K.Z),C=(0,n.useContext)(k.ZP.ConfigContext),x=C.getPrefixCls,P=e.prefixCls||x("pro"),E="".concat(P,"-page-container"),O=S()(E,c,(r={},(0,A.Z)(r,"".concat(P,"-page-container-ghost"),v),(0,A.Z)(r,"".concat(P,"-page-container-with-footer"),f),r)),y=(0,n.useMemo)(function(){return a?n.createElement(n.Fragment,null,n.createElement("div",{className:"".concat(E,"-children-content")},a),b.hasFooterToolbar&&n.createElement("div",{style:{height:48,marginTop:24}})):null},[a,E,b.hasFooterToolbar]),N=(0,n.useMemo)(function(){var T;return g==!1?!1:g||(h==null||(T=h.header)===null||T===void 0?void 0:T.breadcrumbRender)},[g,h==null||(t=h.header)===null||t===void 0?void 0:t.breadcrumbRender]),B=n.createElement(Ft,(0,R.Z)({},h,{breadcrumbRender:N,ghost:v,prefixCls:void 0,prefixedClassName:E})),Z=(0,n.useMemo)(function(){if(n.isValidElement(s))return s;if(typeof s=="boolean"&&!s)return null;var T=St(s);return n.createElement(Nt.Z,T)},[s]),F=(0,n.useMemo)(function(){var T=Z||y;return e.waterMarkProps||b.waterMarkProps?n.createElement(Tt,e.waterMarkProps||b.waterMarkProps,T):T},[e.waterMarkProps,b.waterMarkProps,Z,y]);return n.createElement("div",{style:u,className:O},l&&B?n.createElement(Le,(0,R.Z)({offsetTop:b.hasHeader&&b.fixedHeader?b.headerHeight:0},m),B):B,F&&n.createElement(xt,null,F),f&&n.createElement(Et,{prefixCls:P},f))},Ht=Mt},56264:function(){},53645:function(){},12395:function(){},70883:function(){},81262:function(){},59903:function(){}}]);