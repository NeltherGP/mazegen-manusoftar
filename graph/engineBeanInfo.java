/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

import java.beans.*;

/**
 *
 * @author manusoftar
 */
public class engineBeanInfo extends SimpleBeanInfo {

    // Bean descriptor//GEN-FIRST:BeanDescriptor
    /*lazy BeanDescriptor*/
    private static BeanDescriptor getBdescriptor(){
        BeanDescriptor beanDescriptor = new BeanDescriptor  ( graph.engine.class , null ); // NOI18N//GEN-HEADEREND:BeanDescriptor

    // Here you can add code for customizing the BeanDescriptor.

        return beanDescriptor;     }//GEN-LAST:BeanDescriptor


    // Property identifiers//GEN-FIRST:Properties
    private static final int PROPERTY_alto = 0;
    private static final int PROPERTY_ancho = 1;
    private static final int PROPERTY_cancelled = 2;
    private static final int PROPERTY_cellCount = 3;
    private static final int PROPERTY_debuging = 4;
    private static final int PROPERTY_delay = 5;
    private static final int PROPERTY_done = 6;
    private static final int PROPERTY_opossingWall = 7;
    private static final int PROPERTY_padre = 8;
    private static final int PROPERTY_progress = 9;
    private static final int PROPERTY_propertyChangeSupport = 10;
    private static final int PROPERTY_state = 11;

    // Property array 
    /*lazy PropertyDescriptor*/
    private static PropertyDescriptor[] getPdescriptor(){
        PropertyDescriptor[] properties = new PropertyDescriptor[12];
    
        try {
            properties[PROPERTY_alto] = new PropertyDescriptor ( "alto", graph.engine.class, null, "setAlto" ); // NOI18N
            properties[PROPERTY_ancho] = new PropertyDescriptor ( "ancho", graph.engine.class, null, "setAncho" ); // NOI18N
            properties[PROPERTY_cancelled] = new PropertyDescriptor ( "cancelled", graph.engine.class, "isCancelled", null ); // NOI18N
            properties[PROPERTY_cellCount] = new PropertyDescriptor ( "cellCount", graph.engine.class, null, "setCellCount" ); // NOI18N
            properties[PROPERTY_debuging] = new PropertyDescriptor ( "debuging", graph.engine.class, null, "setDebuging" ); // NOI18N
            properties[PROPERTY_delay] = new PropertyDescriptor ( "delay", graph.engine.class, null, "setDelay" ); // NOI18N
            properties[PROPERTY_done] = new PropertyDescriptor ( "done", graph.engine.class, "isDone", null ); // NOI18N
            properties[PROPERTY_opossingWall] = new IndexedPropertyDescriptor ( "opossingWall", graph.engine.class, null, null, "getOpossingWall", null ); // NOI18N
            properties[PROPERTY_padre] = new PropertyDescriptor ( "padre", graph.engine.class, null, "setPadre" ); // NOI18N
            properties[PROPERTY_progress] = new PropertyDescriptor ( "progress", graph.engine.class, "getProgress", null ); // NOI18N
            properties[PROPERTY_propertyChangeSupport] = new PropertyDescriptor ( "propertyChangeSupport", graph.engine.class, "getPropertyChangeSupport", null ); // NOI18N
            properties[PROPERTY_state] = new PropertyDescriptor ( "state", graph.engine.class, "getState", null ); // NOI18N
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Properties

    // Here you can add code for customizing the properties array.

        return properties;     }//GEN-LAST:Properties

    // EventSet identifiers//GEN-FIRST:Events
    private static final int EVENT_propertyChangeListener = 0;

    // EventSet array
    /*lazy EventSetDescriptor*/
    private static EventSetDescriptor[] getEdescriptor(){
        EventSetDescriptor[] eventSets = new EventSetDescriptor[1];
    
        try {
            eventSets[EVENT_propertyChangeListener] = new EventSetDescriptor ( graph.engine.class, "propertyChangeListener", java.beans.PropertyChangeListener.class, new String[] {"propertyChange"}, "addPropertyChangeListener", "removePropertyChangeListener" ); // NOI18N
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Events

    // Here you can add code for customizing the event sets array.

        return eventSets;     }//GEN-LAST:Events

    // Method identifiers//GEN-FIRST:Methods
    private static final int METHOD_abort0 = 0;
    private static final int METHOD_cancel1 = 1;
    private static final int METHOD_countVecinosLibres2 = 2;
    private static final int METHOD_doInBackground3 = 3;
    private static final int METHOD_execute4 = 4;
    private static final int METHOD_firePropertyChange5 = 5;
    private static final int METHOD_get6 = 6;
    private static final int METHOD_get7 = 7;
    private static final int METHOD_getCellNumber8 = 8;
    private static final int METHOD_getVecinosCount9 = 9;
    private static final int METHOD_getVecinoX10 = 10;
    private static final int METHOD_getVecinoY11 = 11;
    private static final int METHOD_hasVecino12 = 12;
    private static final int METHOD_init13 = 13;
    private static final int METHOD_run14 = 14;
    private static final int METHOD_showConstruction15 = 15;

    // Method array 
    /*lazy MethodDescriptor*/
    private static MethodDescriptor[] getMdescriptor(){
        MethodDescriptor[] methods = new MethodDescriptor[16];
    
        try {
            methods[METHOD_abort0] = new MethodDescriptor(graph.engine.class.getMethod("abort", new Class[] {})); // NOI18N
            methods[METHOD_abort0].setDisplayName ( "" );
            methods[METHOD_cancel1] = new MethodDescriptor(javax.swing.SwingWorker.class.getMethod("cancel", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_cancel1].setDisplayName ( "" );
            methods[METHOD_countVecinosLibres2] = new MethodDescriptor(graph.engine.class.getMethod("countVecinosLibres", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_countVecinosLibres2].setDisplayName ( "" );
            methods[METHOD_doInBackground3] = new MethodDescriptor(graph.engine.class.getMethod("doInBackground", new Class[] {})); // NOI18N
            methods[METHOD_doInBackground3].setDisplayName ( "" );
            methods[METHOD_execute4] = new MethodDescriptor(javax.swing.SwingWorker.class.getMethod("execute", new Class[] {})); // NOI18N
            methods[METHOD_execute4].setDisplayName ( "" );
            methods[METHOD_firePropertyChange5] = new MethodDescriptor(javax.swing.SwingWorker.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, java.lang.Object.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_firePropertyChange5].setDisplayName ( "" );
            methods[METHOD_get6] = new MethodDescriptor(javax.swing.SwingWorker.class.getMethod("get", new Class[] {})); // NOI18N
            methods[METHOD_get6].setDisplayName ( "" );
            methods[METHOD_get7] = new MethodDescriptor(javax.swing.SwingWorker.class.getMethod("get", new Class[] {long.class, java.util.concurrent.TimeUnit.class})); // NOI18N
            methods[METHOD_get7].setDisplayName ( "" );
            methods[METHOD_getCellNumber8] = new MethodDescriptor(graph.engine.class.getMethod("getCellNumber", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_getCellNumber8].setDisplayName ( "" );
            methods[METHOD_getVecinosCount9] = new MethodDescriptor(graph.engine.class.getMethod("getVecinosCount", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_getVecinosCount9].setDisplayName ( "" );
            methods[METHOD_getVecinoX10] = new MethodDescriptor(graph.engine.class.getMethod("getVecinoX", new Class[] {int.class, int.class, int.class})); // NOI18N
            methods[METHOD_getVecinoX10].setDisplayName ( "" );
            methods[METHOD_getVecinoY11] = new MethodDescriptor(graph.engine.class.getMethod("getVecinoY", new Class[] {int.class, int.class, int.class})); // NOI18N
            methods[METHOD_getVecinoY11].setDisplayName ( "" );
            methods[METHOD_hasVecino12] = new MethodDescriptor(graph.engine.class.getMethod("hasVecino", new Class[] {int.class, int.class, int.class})); // NOI18N
            methods[METHOD_hasVecino12].setDisplayName ( "" );
            methods[METHOD_init13] = new MethodDescriptor(graph.engine.class.getMethod("init", new Class[] {})); // NOI18N
            methods[METHOD_init13].setDisplayName ( "" );
            methods[METHOD_run14] = new MethodDescriptor(javax.swing.SwingWorker.class.getMethod("run", new Class[] {})); // NOI18N
            methods[METHOD_run14].setDisplayName ( "" );
            methods[METHOD_showConstruction15] = new MethodDescriptor(graph.engine.class.getMethod("showConstruction", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_showConstruction15].setDisplayName ( "" );
        }
        catch( Exception e) {}//GEN-HEADEREND:Methods

    // Here you can add code for customizing the methods array.
    
        return methods;     }//GEN-LAST:Methods

    private static java.awt.Image iconColor16 = null;//GEN-BEGIN:IconsDef
    private static java.awt.Image iconColor32 = null;
    private static java.awt.Image iconMono16 = null;
    private static java.awt.Image iconMono32 = null;//GEN-END:IconsDef
    private static String iconNameC16 = null;//GEN-BEGIN:Icons
    private static String iconNameC32 = null;
    private static String iconNameM16 = null;
    private static String iconNameM32 = null;//GEN-END:Icons

    private static final int defaultPropertyIndex = -1;//GEN-BEGIN:Idx
    private static final int defaultEventIndex = -1;//GEN-END:Idx

    
//GEN-FIRST:Superclass

    // Here you can add code for customizing the Superclass BeanInfo.

//GEN-LAST:Superclass
	
    /**
     * Gets the bean's <code>BeanDescriptor</code>s.
     * 
     * @return BeanDescriptor describing the editable
     * properties of this bean.  May return null if the
     * information should be obtained by automatic analysis.
     */
    public BeanDescriptor getBeanDescriptor() {
	return getBdescriptor();
    }

    /**
     * Gets the bean's <code>PropertyDescriptor</code>s.
     * 
     * @return An array of PropertyDescriptors describing the editable
     * properties supported by this bean.  May return null if the
     * information should be obtained by automatic analysis.
     * <p>
     * If a property is indexed, then its entry in the result array will
     * belong to the IndexedPropertyDescriptor subclass of PropertyDescriptor.
     * A client of getPropertyDescriptors can use "instanceof" to check
     * if a given PropertyDescriptor is an IndexedPropertyDescriptor.
     */
    public PropertyDescriptor[] getPropertyDescriptors() {
	return getPdescriptor();
    }

    /**
     * Gets the bean's <code>EventSetDescriptor</code>s.
     * 
     * @return  An array of EventSetDescriptors describing the kinds of 
     * events fired by this bean.  May return null if the information
     * should be obtained by automatic analysis.
     */
    public EventSetDescriptor[] getEventSetDescriptors() {
	return getEdescriptor();
    }

    /**
     * Gets the bean's <code>MethodDescriptor</code>s.
     * 
     * @return  An array of MethodDescriptors describing the methods 
     * implemented by this bean.  May return null if the information
     * should be obtained by automatic analysis.
     */
    public MethodDescriptor[] getMethodDescriptors() {
	return getMdescriptor();
    }

    /**
     * A bean may have a "default" property that is the property that will
     * mostly commonly be initially chosen for update by human's who are 
     * customizing the bean.
     * @return  Index of default property in the PropertyDescriptor array
     * 		returned by getPropertyDescriptors.
     * <P>	Returns -1 if there is no default property.
     */
    public int getDefaultPropertyIndex() {
        return defaultPropertyIndex;
    }

    /**
     * A bean may have a "default" event that is the event that will
     * mostly commonly be used by human's when using the bean. 
     * @return Index of default event in the EventSetDescriptor array
     *		returned by getEventSetDescriptors.
     * <P>	Returns -1 if there is no default event.
     */
    public int getDefaultEventIndex() {
        return defaultEventIndex;
    }

    /**
     * This method returns an image object that can be used to
     * represent the bean in toolboxes, toolbars, etc.   Icon images
     * will typically be GIFs, but may in future include other formats.
     * <p>
     * Beans aren't required to provide icons and may return null from
     * this method.
     * <p>
     * There are four possible flavors of icons (16x16 color,
     * 32x32 color, 16x16 mono, 32x32 mono).  If a bean choses to only
     * support a single icon we recommend supporting 16x16 color.
     * <p>
     * We recommend that icons have a "transparent" background
     * so they can be rendered onto an existing background.
     *
     * @param  iconKind  The kind of icon requested.  This should be
     *    one of the constant values ICON_COLOR_16x16, ICON_COLOR_32x32, 
     *    ICON_MONO_16x16, or ICON_MONO_32x32.
     * @return  An image object representing the requested icon.  May
     *    return null if no suitable icon is available.
     */
    public java.awt.Image getIcon(int iconKind) {
        switch ( iconKind ) {
        case ICON_COLOR_16x16:
            if ( iconNameC16 == null )
                return null;
            else {
                if( iconColor16 == null )
                    iconColor16 = loadImage( iconNameC16 );
                return iconColor16;
            }
        case ICON_COLOR_32x32:
            if ( iconNameC32 == null )
                return null;
            else {
                if( iconColor32 == null )
                    iconColor32 = loadImage( iconNameC32 );
                return iconColor32;
            }
        case ICON_MONO_16x16:
            if ( iconNameM16 == null )
                return null;
            else {
                if( iconMono16 == null )
                    iconMono16 = loadImage( iconNameM16 );
                return iconMono16;
            }
        case ICON_MONO_32x32:
            if ( iconNameM32 == null )
                return null;
            else {
                if( iconMono32 == null )
                    iconMono32 = loadImage( iconNameM32 );
                return iconMono32;
            }
	default: return null;
        }
    }

}

