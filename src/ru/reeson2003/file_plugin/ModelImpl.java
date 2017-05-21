package ru.reeson2003.file_plugin;

import ru.reeson2003.Model;
import ru.reeson2003.Parameter;

import java.util.Arrays;
import java.util.List;

/**
 * Date: 21.05.2017.
 * Time: 21:44.
 *
 * @author Pavel Gavrilov.
 */
public class ModelImpl implements Model {
    private String moduleName = "File Plugin";
    private Parameter parameter = new ParameterImpl();
    @Override
    public String getModuleName() {
        return moduleName;
    }

    @Override
    public List<Parameter> getParameters() {
        return Arrays.asList(parameter);
    }

    @Override
    public Parameter getParameter(String s) {
        return parameter;
    }
}
