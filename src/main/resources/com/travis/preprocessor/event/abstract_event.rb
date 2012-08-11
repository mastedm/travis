class AbstractEvent
  @regexp = /.*/
  
  def self.match(str)
    m = str.match @regexp
    return m != nil
  end
  
  def self.get_regexp
    @regexp
  end
end